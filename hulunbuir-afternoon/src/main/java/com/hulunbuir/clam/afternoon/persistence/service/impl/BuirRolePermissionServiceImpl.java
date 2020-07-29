package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirRolePermission;
import com.hulunbuir.clam.afternoon.persistence.mapper.BuirRolePermissionMapper;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirRolePermissionService;
import com.hulunbuir.clam.common.base.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色权限关联表 Service实现
 *
 * @author wangjunming
 * @since 2020-07-23 17:58:42
 */
@Service
public class BuirRolePermissionServiceImpl implements IBuirRolePermissionService {

    @Autowired
    private BuirRolePermissionMapper buirRolePermissionMapper;

   /**
    * 角色权限关联表分页列表
    *
    * @param queryRequest
    * @param buirRolePermission
    * @author wangjunming
    * @since 2020-07-23 17:58:42
    */
    @Override
    public IPage<BuirRolePermission> buirRolePermissionPage(QueryRequest queryRequest, BuirRolePermission buirRolePermission) {
        LambdaQueryWrapper<BuirRolePermission> queryWrapper = initQueryWrapper(queryRequest,buirRolePermission);
        Page<BuirRolePermission> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return buirRolePermissionMapper.selectPage(page, queryWrapper);
    }

    /**
    * 列表的查询参数
    *
    * @author wangjunming
    * @since 2020-07-23 17:58:42
    */
    private LambdaQueryWrapper<BuirRolePermission> initQueryWrapper(QueryRequest queryRequest, BuirRolePermission buirRolePermission) {
        LambdaQueryWrapper<BuirRolePermission> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

   /**
    * 保存
    *
    * @param buirRolePermission
    * @author wangjunming
    * @since 2020-07-23 17:58:42
    */
    @Override
    @Transactional
    public boolean saveBuirRolePermission(BuirRolePermission buirRolePermission) {
        //--TODO 做一些初始化动作
        return buirRolePermissionMapper.insert(buirRolePermission)>0;
    }

   /**
    * 修改
    *
    * @param buirRolePermission
    * @author wangjunming
    * @since 2020-07-23 17:58:42
    */
    @Override
    @Transactional
    public boolean updateBuirRolePermission(BuirRolePermission buirRolePermission) {
        //--TODO 做一些效验动作
        return buirRolePermissionMapper.updateById(buirRolePermission)>0;
    }

   /**
    * 获取单个
    *
    * @param buirRolePermission
    * @author wangjunming
    * @since 2020-07-23 17:58:42
    */
    @Override
    public BuirRolePermission getOneBuirRolePermission(BuirRolePermission buirRolePermission) {
    LambdaQueryWrapper<BuirRolePermission> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO 初始化查询条件
        return buirRolePermissionMapper.selectOne(queryWrapper);
    }


}
