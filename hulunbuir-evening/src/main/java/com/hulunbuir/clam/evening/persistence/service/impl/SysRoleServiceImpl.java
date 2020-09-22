package com.hulunbuir.clam.evening.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.SysRole;
import com.hulunbuir.clam.evening.persistence.mapper.SysRoleMapper;
import com.hulunbuir.clam.evening.persistence.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色表 Service实现
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

   /**
    * 角色表分页列表
    *
    * @param queryRequest
    * @param sysRole
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    public IPage<SysRole> page(QueryRequest queryRequest, SysRole sysRole) {
        LambdaQueryWrapper<SysRole> queryWrapper = initQueryWrapper(queryRequest,sysRole);
        Page<SysRole> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return sysRoleMapper.selectPage(page, queryWrapper);
    }

    /**
    * 列表的查询参数
    *
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    private LambdaQueryWrapper<SysRole> initQueryWrapper(QueryRequest queryRequest, SysRole sysRole) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

   /**
    * 保存
    *
    * @param sysRole
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    @Transactional
    public boolean save(SysRole sysRole) {
        //--TODO 做一些初始化动作
        return sysRoleMapper.insert(sysRole)>0;
    }

   /**
    * 修改
    *
    * @param sysRole
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    @Transactional
    public boolean update(SysRole sysRole) {
        //--TODO 做一些效验动作
        return sysRoleMapper.updateById(sysRole)>0;
    }

   /**
    * 获取单个
    *
    * @param sysRole
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    public SysRole selOne(SysRole sysRole) {
    LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO 初始化查询条件
        return sysRoleMapper.selectOne(queryWrapper);
    }


}
