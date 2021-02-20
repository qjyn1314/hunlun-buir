package com.hulunbuir.evening.generationcode.service.impl;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceCreator;
import com.baomidou.dynamic.datasource.ds.GroupDataSource;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.datasource.support.DataSourceConstants;
import com.hulunbuir.common.base.QueryRequest;
import com.hulunbuir.common.config.ApplicationUtil;
import com.hulunbuir.evening.generationcode.entity.DatasourceConf;
import com.hulunbuir.evening.generationcode.mapper.DatasourceConfMapper;
import com.hulunbuir.evening.generationcode.service.IDatasourceConfService;
import com.hulunbuir.parent.exception.HulunBuirException;
import com.hulunbuir.parent.tool.JasyptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 数据源表 Service实现
 *
 * @author Mr.Wang
 * @since 2020-10-20 14:32:13
 */
@Slf4j
@Service
public class DatasourceConfServiceImpl implements IDatasourceConfService {

    @Autowired
    private DatasourceConfMapper datasourceConfMapper;

    @Resource
    private DataSourceCreator dataSourceCreator;

    /**
     * 数据源表分页列表
     *
     * @param queryRequest
     * @param datasourceConf
     * @author Mr.Wang
     * @since 2020-10-20 14:32:13
     */
    @Override
    public IPage<DatasourceConf> page(QueryRequest queryRequest, DatasourceConf datasourceConf) {
        LambdaQueryWrapper<DatasourceConf> queryWrapper = initQueryWrapper(queryRequest, datasourceConf);
        Page<DatasourceConf> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return datasourceConfMapper.selectPage(page, queryWrapper);
    }

    /**
     * 列表的查询参数
     *
     * @author Mr.Wang
     * @since 2020-10-20 14:32:13
     */
    private LambdaQueryWrapper<DatasourceConf> initQueryWrapper(QueryRequest queryRequest, DatasourceConf datasourceConf) {
        LambdaQueryWrapper<DatasourceConf> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

    /**
     * 保存
     *
     * @param datasourceConf
     * @author Mr.Wang
     * @since 2020-10-20 14:32:13
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DatasourceConf datasourceConf) throws HulunBuirException {
        if (checkDataSource(datasourceConf)) {
            HulunBuirException.build("请检查数据库信息是否正确，数据库连接失败！");
        }
        // 添加数据源到springioc容器中
        addDataSourceToIoc(datasourceConf);
        datasourceConf.setPassword(JasyptUtil.encryptPwd(datasourceConf.getPassword()));
        return datasourceConfMapper.insert(datasourceConf) > 0;
    }

    private void addDataSourceToIoc(DatasourceConf datasourceConf) {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setPoolName(datasourceConf.getDatabaseName());
        dataSourceProperty.setUrl(datasourceConf.getUrl());
        dataSourceProperty.setUsername(datasourceConf.getUsername());
        dataSourceProperty.setPassword(datasourceConf.getPassword());
        dataSourceProperty.setDriverClassName(DataSourceConstants.DS_DRIVER);
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        ApplicationUtil.getBean(DynamicRoutingDataSource.class).addDataSource(dataSourceProperty.getPoolName(), dataSource);
    }

    /**
     * 修改
     *
     * @param datasourceConf
     * @author Mr.Wang
     * @since 2020-10-20 14:32:13
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(DatasourceConf datasourceConf) throws HulunBuirException {
        if (checkDataSource(datasourceConf)) {
            HulunBuirException.build("请检查数据库信息是否正确，数据库连接失败！");
        }
        final DatasourceConf selOne = selOne(datasourceConf);
        ApplicationUtil.getBean(DynamicRoutingDataSource.class)
                .removeDataSource(selOne.getDatabaseName());
        addDataSourceToIoc(datasourceConf);
        datasourceConf.setPassword(JasyptUtil.encryptPwd(datasourceConf.getPassword()));
        return datasourceConfMapper.updateById(datasourceConf) > 0;
    }

    /**
     * 获取单个
     *
     * @param datasourceConf
     * @author Mr.Wang
     * @since 2020-10-20 14:32:13
     */
    @Override
    public DatasourceConf selOne(DatasourceConf datasourceConf) {
        LambdaQueryWrapper<DatasourceConf> queryWrapper = new LambdaQueryWrapper<>();
        if (null != datasourceConf.getId()) {
            queryWrapper.eq(DatasourceConf::getId, datasourceConf.getId());
        }
        return datasourceConfMapper.selectOne(queryWrapper);
    }

    /**
     * 效验数据源
     *
     * @param conf
     * @author wangjunming
     * @since 2020/11/3 20:32
     */
    @Override
    public boolean checkDataSource(DatasourceConf conf) {
        try {
            DriverManager.getConnection(conf.getUrl(), conf.getUsername(), conf.getPassword());
        } catch (SQLException e) {
            log.error("数据源配置 {} , 获取链接失败", conf.getDatabaseName(), e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 查询数据源列表
     *
     * @author wangjunming
     * @since 2020/11/3 20:57
     */
    @Override
    public List<DatasourceConf> dataSourceConfList() {
        final Map<String, DataSource> beforeCurrentDataSources = ApplicationUtil.getBean(DynamicRoutingDataSource.class).getCurrentDataSources();
        log.info("当前SpringIoc中的动态数据源有：{},",beforeCurrentDataSources);

        final Map<String, GroupDataSource> currentGroupDataSources = ApplicationUtil.getBean(DynamicRoutingDataSource.class).getCurrentGroupDataSources();
        log.info("当前SpringIoc中的动态数据源有：{},",currentGroupDataSources);
        //手动切换数据源
        DynamicDataSourceContextHolder.push("steta_order");
        final Map<String, DataSource> afterCurrentDataSources = ApplicationUtil.getBean(DynamicRoutingDataSource.class).getCurrentDataSources();
        log.info("当前SpringIoc中的动态数据源有：{},",afterCurrentDataSources);
        try {
            final List<DatasourceConf> datasourceConfs = datasourceConfMapper.selectList(initQueryWrapper(null, null));
        } catch (Exception e) {
            log.error("查询失败:>{}",e.getMessage());
        }
        //将数据源切换至默认数据源
        DynamicDataSourceContextHolder.clear();
        return datasourceConfMapper.selectList(initQueryWrapper(null, null));
    }


}
