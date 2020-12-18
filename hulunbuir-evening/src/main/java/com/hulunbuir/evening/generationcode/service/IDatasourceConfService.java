package com.hulunbuir.evening.generationcode.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.common.base.QueryRequest;
import com.hulunbuir.evening.generationcode.entity.DatasourceConf;
import com.hulunbuir.parent.exception.HulunBuirException;

import java.util.List;

/**
 * 数据源表 Service接口
 *
 * @author Mr.Wang
 * @since 2020-10-20 14:32:13
 */
public interface IDatasourceConfService {

    /**
     * 数据源表分页列表
     *
     * @author Mr.Wang
     * @since 2020-10-20 14:32:13
     */
    IPage<DatasourceConf> page(QueryRequest queryRequest, DatasourceConf datasourceConf);

    /**
     * 保存
     *
     * @author Mr.Wang
     * @since 2020-10-20 14:32:13
     */
     boolean save(DatasourceConf datasourceConf) throws HulunBuirException;

    /**
     * 修改
     *
     * @author Mr.Wang
     * @since 2020-10-20 14:32:13
     */
     boolean update(DatasourceConf datasourceConf) throws HulunBuirException;


    /**
     * 获取单个
     *
     * @author Mr.Wang
     * @since 2020-10-20 14:32:13
     */
    DatasourceConf selOne(DatasourceConf datasourceConf);

    /**
     * 效验数据源
     *
     * @author wangjunming
     * @since 2020/11/3 20:32
     */
    boolean checkDataSource(DatasourceConf datasourceConf);

    /**
     * 查询数据源列表
     *
     * @author wangjunming
     * @since 2020/11/3 20:57
     */
    List<DatasourceConf> dataSourceConfList();


}
