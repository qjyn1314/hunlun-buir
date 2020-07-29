package com.hulunbuir.clam.afternoon.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirRolePermission;
import com.hulunbuir.clam.common.base.QueryRequest;

/**
 * 角色权限关联表 Service接口
 *
 * @author wangjunming
 * @since 2020-07-23 17:58:42
 */
public interface IBuirRolePermissionService {

    /**
     * 角色权限关联表分页列表
     *
     * @author wangjunming
     * @since 2020-07-23 17:58:42
     */
    IPage<BuirRolePermission> buirRolePermissionPage(QueryRequest queryRequest, BuirRolePermission buirRolePermission);

    /**
     * 保存
     *
     * @author wangjunming
     * @since 2020-07-23 17:58:42
     */
     boolean saveBuirRolePermission(BuirRolePermission buirRolePermission);

    /**
     * 修改
     *
     * @author wangjunming
     * @since 2020-07-23 17:58:42
     */
     boolean updateBuirRolePermission(BuirRolePermission buirRolePermission);


    /**
     * 获取单个
     *
     * @author wangjunming
     * @since 2020-07-23 17:58:42
     */
    BuirRolePermission getOneBuirRolePermission(BuirRolePermission buirRolePermission);


}
