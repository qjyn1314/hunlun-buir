package com.hulunbuir.clam.evening.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.SysPermission;
import com.hulunbuir.clam.evening.persistence.vo.LayPermissionTree;
import com.hulunbuir.clam.evening.persistence.vo.SysPermissionTree;

import java.util.List;

/**
 * 权限表 Service接口
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
public interface ISysPermissionService {

    /**
     * 权限表分页列表
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    IPage<SysPermission> page(QueryRequest queryRequest, SysPermission sysPermission);

    /**
     * 保存
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
     boolean save(SysPermission sysPermission);

    /**
     * 修改
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
     boolean update(SysPermission sysPermission);


    /**
     * 获取单个
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    SysPermission selOne(SysPermission sysPermission);


    /**
     * 当前登录用户的权限列表
     *
     * @author wangjunming
     * @since 2020/9/25 18:00
     * @return
     */
    List<SysPermissionTree> permissionTree(SysPermissionTree permissionTree, Integer userId);

    /**
     * 添加权限页面的权限树
     *
     * @author wangjunming
     * @since 2020/9/27 15:54
     */
    List<LayPermissionTree> layPermissionTree(LayPermissionTree layPermissionTree);

}
