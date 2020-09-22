package com.hulunbuir.clam.evening.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.SysPermission;
import com.hulunbuir.clam.evening.persistence.mapper.SysPermissionMapper;
import com.hulunbuir.clam.evening.persistence.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限表 Service实现
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

   /**
    * 权限表分页列表
    *
    * @param queryRequest
    * @param sysPermission
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    public IPage<SysPermission> page(QueryRequest queryRequest, SysPermission sysPermission) {
        LambdaQueryWrapper<SysPermission> queryWrapper = initQueryWrapper(queryRequest,sysPermission);
        Page<SysPermission> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return sysPermissionMapper.selectPage(page, queryWrapper);
    }

    /**
    * 列表的查询参数
    *
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    private LambdaQueryWrapper<SysPermission> initQueryWrapper(QueryRequest queryRequest, SysPermission sysPermission) {
        LambdaQueryWrapper<SysPermission> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

   /**
    * 保存
    *
    * @param sysPermission
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    @Transactional
    public boolean save(SysPermission sysPermission) {
        //--TODO 做一些初始化动作
        return sysPermissionMapper.insert(sysPermission)>0;
    }

   /**
    * 修改
    *
    * @param sysPermission
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    @Transactional
    public boolean update(SysPermission sysPermission) {
        //--TODO 做一些效验动作
        return sysPermissionMapper.updateById(sysPermission)>0;
    }

   /**
    * 获取单个
    *
    * @param sysPermission
    * @author Mr.Wang
    * @since 2020-09-22 11:04:50
    */
    @Override
    public SysPermission selOne(SysPermission sysPermission) {
    LambdaQueryWrapper<SysPermission> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO 初始化查询条件
        return sysPermissionMapper.selectOne(queryWrapper);
    }


}
