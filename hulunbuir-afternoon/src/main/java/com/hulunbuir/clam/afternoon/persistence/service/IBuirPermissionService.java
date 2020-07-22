package com.hulunbuir.clam.afternoon.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirPermission;
import com.hulunbuir.clam.common.base.QueryRequest;

/**
 * 权限表 Service接口
 *
 * @author wangjunming
 * @since 2020-07-22 14:23:29
 */
public interface IBuirPermissionService {

    /**
     * 权限表分页列表
     *
     * @author wangjunming
     * @since 2020-07-22 14:23:29
     */
    IPage<BuirPermission> buirPermissionPage(QueryRequest queryRequest, BuirPermission buirPermission);

    /**
     * 保存
     *
     * @author wangjunming
     * @since 2020-07-22 14:23:29
     */
     boolean saveBuirPermission(BuirPermission buirPermission);

    /**
     * 修改
     *
     * @author wangjunming
     * @since 2020-07-22 14:23:29
     */
     boolean updateBuirPermission(BuirPermission buirPermission);


    /**
     * 获取单个
     *
     * @author wangjunming
     * @since 2020-07-22 14:23:29
     */
    BuirPermission getOneBuirPermission(BuirPermission buirPermission);


}
