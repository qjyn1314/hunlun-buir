package com.hulunbuir.clam.evening.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.SysUserRole;
import com.hulunbuir.clam.evening.persistence.mapper.SysUserRoleMapper;
import com.hulunbuir.clam.evening.persistence.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户角色关联表 Service实现
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 用户角色关联表分页列表
     *
     * @param queryRequest
     * @param sysUserRole
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    @Override
    public IPage<SysUserRole> page(QueryRequest queryRequest, SysUserRole sysUserRole) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = initQueryWrapper(queryRequest, sysUserRole);
        Page<SysUserRole> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return sysUserRoleMapper.selectPage(page, queryWrapper);
    }

    /**
     * 列表的查询参数
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    private LambdaQueryWrapper<SysUserRole> initQueryWrapper(QueryRequest queryRequest, SysUserRole sysUserRole) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

    /**
     * 保存
     *
     * @param sysUserRole
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SysUserRole sysUserRole) {
        //--TODO 做一些初始化动作
        return sysUserRoleMapper.insert(sysUserRole) > 0;
    }

    /**
     * 修改
     *
     * @param sysUserRole
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(SysUserRole sysUserRole) {
        //--TODO 做一些效验动作
        return sysUserRoleMapper.updateById(sysUserRole) > 0;
    }

    /**
     * 获取单个
     *
     * @param sysUserRole
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    @Override
    public SysUserRole selOne(SysUserRole sysUserRole) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO 初始化查询条件
        return sysUserRoleMapper.selectOne(queryWrapper);
    }

    /**
     * 通过用户ID删除角色中间表
     *
     * @param userId
     * @author wangjunming
     * @since 2020/9/29 16:57
     */
    @Override
    public boolean del(Long userId) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, userId);
        return sysUserRoleMapper.delete(queryWrapper) > 0;
    }


}
