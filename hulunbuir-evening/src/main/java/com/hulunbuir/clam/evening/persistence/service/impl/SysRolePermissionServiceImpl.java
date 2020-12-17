package com.hulunbuir.clam.evening.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.SysRolePermission;
import com.hulunbuir.clam.evening.persistence.mapper.SysRolePermissionMapper;
import com.hulunbuir.clam.evening.persistence.service.ISysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色权限关联表 Service实现
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
@Service
public class SysRolePermissionServiceImpl implements ISysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

   /**
    * 角色权限关联表分页列表
    *
    * @param queryRequest
    * @param sysRolePermission
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    public IPage<SysRolePermission> page(QueryRequest queryRequest, SysRolePermission sysRolePermission) {
        LambdaQueryWrapper<SysRolePermission> queryWrapper = initQueryWrapper(queryRequest,sysRolePermission);
        Page<SysRolePermission> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return sysRolePermissionMapper.selectPage(page, queryWrapper);
    }

    /**
    * 列表的查询参数
    *
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    private LambdaQueryWrapper<SysRolePermission> initQueryWrapper(QueryRequest queryRequest, SysRolePermission sysRolePermission) {
        LambdaQueryWrapper<SysRolePermission> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

   /**
    * 保存
    *
    * @param sysRolePermission
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    @Transactional
    public boolean save(SysRolePermission sysRolePermission) {
        //--TODO 做一些初始化动作
        return sysRolePermissionMapper.insert(sysRolePermission)>0;
    }

   /**
    * 修改
    *
    * @param sysRolePermission
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    @Transactional
    public boolean update(SysRolePermission sysRolePermission) {
        //--TODO 做一些效验动作
        return sysRolePermissionMapper.updateById(sysRolePermission)>0;
    }

   /**
    * 获取单个
    *
    * @param sysRolePermission
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    public SysRolePermission selOne(SysRolePermission sysRolePermission) {
    LambdaQueryWrapper<SysRolePermission> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO 初始化查询条件
        return sysRolePermissionMapper.selectOne(queryWrapper);
    }

    /**
     * 根据角色ID获取权限列表
     *
     * @param roleId
     * @author wangjunming
     * @since 2020/9/27 17:49
     */
    @Override
    public List<SysRolePermission> selList(Integer roleId) {
        LambdaQueryWrapper<SysRolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRolePermission::getRoleId,roleId);
        return sysRolePermissionMapper.selectList(queryWrapper);
    }

    /**
     * 根据角色ID删除权限中间表
     *
     * @param id
     * @author wangjunming
     * @since 2020/9/27 18:06
     */
    @Override
    public boolean del(Integer roleId) {
        LambdaQueryWrapper<SysRolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRolePermission::getRoleId,roleId);
        return sysRolePermissionMapper.delete(queryWrapper)>0;
    }


}
