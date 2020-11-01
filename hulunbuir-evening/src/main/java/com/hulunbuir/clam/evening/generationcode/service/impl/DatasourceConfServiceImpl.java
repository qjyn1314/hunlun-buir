package com.hulunbuir.clam.evening.generationcode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.generationcode.entity.DatasourceConf;
import com.hulunbuir.clam.evening.generationcode.mapper.DatasourceConfMapper;
import com.hulunbuir.clam.evening.generationcode.service.IDatasourceConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据源表 Service实现
 *
 * @author Mr.Wang
 * @since 2020-10-20 14:32:13
 */
@Service
public class DatasourceConfServiceImpl implements IDatasourceConfService {

    @Autowired
    private DatasourceConfMapper datasourceConfMapper;

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
        LambdaQueryWrapper<DatasourceConf> queryWrapper = initQueryWrapper(queryRequest,datasourceConf);
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
    @Transactional
    public boolean save(DatasourceConf datasourceConf) {
        //--TODO 做一些初始化动作
        return datasourceConfMapper.insert(datasourceConf)>0;
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
    public boolean update(DatasourceConf datasourceConf) {
//        DynamicDataSourceHolder.setDataSource("contentDataSource");
//        DynamicDataSourceContextHolder.peek();
        //--TODO 做一些效验动作
        return datasourceConfMapper.updateById(datasourceConf)>0;
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
        //--TODO 初始化查询条件
        return datasourceConfMapper.selectOne(queryWrapper);
    }


}
