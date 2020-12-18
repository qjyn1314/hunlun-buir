package com.hulunbuir.evening.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.evening.persistence.entity.SysUserRole;
import com.hulunbuir.common.base.QueryRequest;

/**
 * 用户角色关联表 Service接口
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
public interface ISysUserRoleService {

    /**
     * 用户角色关联表分页列表
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    IPage<SysUserRole> page(QueryRequest queryRequest, SysUserRole sysUserRole);

    /**
     * 保存
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
     boolean save(SysUserRole sysUserRole);

    /**
     * 修改
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
     boolean update(SysUserRole sysUserRole);


    /**
     * 获取单个
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    SysUserRole selOne(SysUserRole sysUserRole);


    /**
     * 通过用户ID删除角色中间表
     *
     * @author wangjunming
     * @since 2020/9/29 16:57
     */
    boolean del(Long userId);

}
