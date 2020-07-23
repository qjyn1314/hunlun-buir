package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirRole;
import com.hulunbuir.clam.afternoon.persistence.mapper.BuirRoleMapper;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirRoleService;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 角色表 Service实现
 *
 * @author wangjunming
 * @since 2020-07-23 10:09:33
 */
@Service
public class BuirRoleServiceImpl implements IBuirRoleService {

    @Autowired
    private BuirRoleMapper buirRoleMapper;

   /**
    * 角色表分页列表
    *
    * @param queryRequest
    * @param buirRole
    * @author wangjunming
    * @since 2020-07-23 10:09:33
    */
    @Override
    public IPage<BuirRole> buirRolePage(QueryRequest queryRequest, BuirRole buirRole) {
        LambdaQueryWrapper<BuirRole> queryWrapper = initQueryWrapper(queryRequest,buirRole);
        Page<BuirRole> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return buirRoleMapper.selectPage(page, queryWrapper);
    }

    /**
    * 列表的查询参数
    *
    * @author wangjunming
    * @since 2020-07-23 10:09:33
    */
    private LambdaQueryWrapper<BuirRole> initQueryWrapper(QueryRequest queryRequest, BuirRole buirRole) {
        LambdaQueryWrapper<BuirRole> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(buirRole.getRoleName())) {
            queryWrapper.like(BuirRole::getRoleName, buirRole.getRoleName());
        }
        if (StringUtils.isNotBlank(buirRole.getRoleCode())) {
            queryWrapper.like(BuirRole::getRoleCode, buirRole.getRoleCode());
        }
        return queryWrapper;
    }

   /**
    * 保存
    *
    * @param buirRole
    * @author wangjunming
    * @since 2020-07-23 10:09:33
    */
    @Override
    @Transactional
    public boolean saveBuirRole(BuirRole buirRole) {
        //--TODO 做一些初始化动作
        return buirRoleMapper.insert(buirRole)>0;
    }

   /**
    * 修改
    *
    * @param buirRole
    * @author wangjunming
    * @since 2020-07-23 10:09:33
    */
    @Override
    @Transactional
    public boolean updateBuirRole(BuirRole buirRole) throws HulunBuirException {
        Integer[] roleIds = {1,2};
        if(Arrays.asList(roleIds).contains(buirRole.getId())){
            throw HulunBuirException.build("请勿修改系统中默认角色！");
        }
        //--TODO 做一些效验动作
        return buirRoleMapper.updateById(buirRole)>0;
    }

   /**
    * 获取单个
    *
    * @param buirRole
    * @author wangjunming
    * @since 2020-07-23 10:09:33
    */
    @Override
    public BuirRole getOneBuirRole(BuirRole buirRole) {
    LambdaQueryWrapper<BuirRole> queryWrapper = new LambdaQueryWrapper<>();
        if(null != buirRole.getId()){
            queryWrapper.eq(BuirRole::getId,buirRole.getId());
        }
        return buirRoleMapper.selectOne(queryWrapper);
    }

    /**
     * 获取列表
     *
     * @param buirRole
     * @author wangjunming
     * @since 2020/7/23 11:15
     */
    @Override
    public List<BuirRole> findInfoList(BuirRole buirRole) {
        LambdaQueryWrapper<BuirRole> queryWrapper = new LambdaQueryWrapper<>();
        return buirRoleMapper.selectList(queryWrapper);
    }


}
