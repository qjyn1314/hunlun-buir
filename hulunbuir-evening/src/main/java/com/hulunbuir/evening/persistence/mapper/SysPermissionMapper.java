package com.hulunbuir.evening.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hulunbuir.evening.persistence.entity.SysPermission;
import com.hulunbuir.evening.persistence.vo.LayPermissionTree;
import com.hulunbuir.evening.persistence.vo.SysPermissionTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限表 Mapper
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 当前登录用户的权限列表
     *
     * @author wangjunming
     * @since 2020/9/25 18:03
     */
    List<SysPermissionTree> getPermissionTree(@Param("per") SysPermissionTree permissionTree, @Param("userId") Integer userId);

    /**
     * 获取权限树列表
     *
     * @author wangjunming
     * @since 2020/9/25 18:03
     */
    List<SysPermissionTree> getPermissionTrees(SysPermissionTree permissionTree);


    /**
     * 添加权限页面的权限树
     *
     * @author wangjunming
     * @since 2020/9/27 15:57
     */
    List<LayPermissionTree> getLayPermissionTree(LayPermissionTree permissionTree);

}
