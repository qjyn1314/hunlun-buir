package com.hulunbuir.clam.evening.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.clam.evening.persistence.entity.SysRolePermission;
import com.hulunbuir.clam.common.base.QueryRequest;

/**
 * 角色权限关联表 Service接口
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
public interface ISysRolePermissionService {

    /**
     * 角色权限关联表分页列表
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    IPage<SysRolePermission> page(QueryRequest queryRequest, SysRolePermission sysRolePermission);

    /**
     * 保存
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
     boolean save(SysRolePermission sysRolePermission);

    /**
     * 修改
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
     boolean update(SysRolePermission sysRolePermission);


    /**
     * 获取单个
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    SysRolePermission selOne(SysRolePermission sysRolePermission);


}
