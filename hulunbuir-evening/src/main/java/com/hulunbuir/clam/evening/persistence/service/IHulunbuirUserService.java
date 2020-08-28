package com.hulunbuir.clam.evening.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.clam.evening.persistence.entity.HulunbuirUser;
import com.hulunbuir.clam.common.base.QueryRequest;

/**
 * 用户表 Service接口
 *
 * @author Mr.Wang
 * @since 2020-08-13 13:40:19
 */
public interface IHulunbuirUserService {

    /**
     * 用户表分页列表
     *
     * @author Mr.Wang
     * @since 2020-08-13 13:40:19
     */
    IPage<HulunbuirUser> page(QueryRequest queryRequest, HulunbuirUser hulunbuirUser);

    /**
     * 保存
     *
     * @author Mr.Wang
     * @since 2020-08-13 13:40:19
     */
     boolean save(HulunbuirUser hulunbuirUser);

    /**
     * 修改
     *
     * @author Mr.Wang
     * @since 2020-08-13 13:40:19
     */
     boolean update(HulunbuirUser hulunbuirUser);


    /**
     * 获取单个
     *
     * @author Mr.Wang
     * @since 2020-08-13 13:40:19
     */
    HulunbuirUser selOne(HulunbuirUser hulunbuirUser);


}
