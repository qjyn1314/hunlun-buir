package com.hulunbuir.clam.evening.generationcode.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.clam.evening.generationcode.entity.DatasourceConf;
import com.hulunbuir.clam.common.base.QueryRequest;

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
     boolean save(DatasourceConf datasourceConf);

    /**
     * 修改
     *
     * @author Mr.Wang
     * @since 2020-10-20 14:32:13
     */
     boolean update(DatasourceConf datasourceConf);


    /**
     * 获取单个
     *
     * @author Mr.Wang
     * @since 2020-10-20 14:32:13
     */
    DatasourceConf selOne(DatasourceConf datasourceConf);


}
