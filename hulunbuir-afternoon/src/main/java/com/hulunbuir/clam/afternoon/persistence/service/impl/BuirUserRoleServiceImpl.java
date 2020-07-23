package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUserRole;
import com.hulunbuir.clam.afternoon.persistence.mapper.BuirUserRoleMapper;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserRoleService;
import com.hulunbuir.clam.common.base.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户角色关联表 Service实现
 *
 * @author wangjunming
 * @since 2020-07-23 12:24:51
 */
@Service
public class BuirUserRoleServiceImpl implements IBuirUserRoleService {

    @Autowired
    private BuirUserRoleMapper buirUserRoleMapper;

   /**
    * 用户角色关联表分页列表
    *
    * @param queryRequest
    * @param buirUserRole
    * @author wangjunming
    * @since 2020-07-23 12:24:51
    */
    @Override
    public IPage<BuirUserRole> buirUserRolePage(QueryRequest queryRequest, BuirUserRole buirUserRole) {
        LambdaQueryWrapper<BuirUserRole> queryWrapper = initQueryWrapper(queryRequest,buirUserRole);
        Page<BuirUserRole> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return buirUserRoleMapper.selectPage(page, queryWrapper);
    }

    /**
    * 列表的查询参数
    *
    * @author wangjunming
    * @since 2020-07-23 12:24:51
    */
    private LambdaQueryWrapper<BuirUserRole> initQueryWrapper(QueryRequest queryRequest, BuirUserRole buirUserRole) {
        LambdaQueryWrapper<BuirUserRole> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

   /**
    * 保存
    *
    * @param buirUserRole
    * @author wangjunming
    * @since 2020-07-23 12:24:51
    */
    @Override
    @Transactional
    public boolean saveBuirUserRole(BuirUserRole buirUserRole) {
        //--TODO 做一些初始化动作
        return buirUserRoleMapper.insert(buirUserRole)>0;
    }

   /**
    * 修改
    *
    * @param buirUserRole
    * @author wangjunming
    * @since 2020-07-23 12:24:51
    */
    @Override
    @Transactional
    public boolean updateBuirUserRole(BuirUserRole buirUserRole) {
        //--TODO 做一些效验动作
        return buirUserRoleMapper.updateById(buirUserRole)>0;
    }

   /**
    * 获取单个
    *
    * @param buirUserRole
    * @author wangjunming
    * @since 2020-07-23 12:24:51
    */
    @Override
    public BuirUserRole getOneBuirUserRole(BuirUserRole buirUserRole) {
    LambdaQueryWrapper<BuirUserRole> queryWrapper = new LambdaQueryWrapper<>();
        if(null != buirUserRole.getRoleId()){
            queryWrapper.eq(BuirUserRole::getRoleId,buirUserRole.getRoleId());
        }
        if(null != buirUserRole.getUserId()){
            queryWrapper.eq(BuirUserRole::getUserId,buirUserRole.getUserId());
        }
        //--TODO 初始化查询条件
        return buirUserRoleMapper.selectOne(queryWrapper);
    }

    /**
     * 删除用户与角色中间表
     *
     * @param userId
     * @author wangjunming
     * @since 2020/7/23 16:22
     */
    @Override
    public boolean delUserRole(Integer userId) {
        LambdaQueryWrapper<BuirUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BuirUserRole::getUserId,userId);
        return buirUserRoleMapper.delete(queryWrapper)>0;
    }


}
