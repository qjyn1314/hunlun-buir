package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirPermission;
import com.hulunbuir.clam.afternoon.persistence.mapper.BuirPermissionMapper;
import com.hulunbuir.clam.afternoon.persistence.qo.BuirPermissionTree;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirPermissionService;
import com.hulunbuir.clam.common.base.QueryRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限表 Service实现
 *
 * @author wangjunming
 * @since 2020-07-22 14:23:29
 */
@Service
public class BuirPermissionServiceImpl implements IBuirPermissionService {

    @Autowired
    private BuirPermissionMapper buirPermissionMapper;

   /**
    * 权限表分页列表
    *
    * @param queryRequest
    * @param buirPermission
    * @author wangjunming
    * @since 2020-07-22 14:23:29
    */
    @Override
    public IPage<BuirPermission> buirPermissionPage(QueryRequest queryRequest, BuirPermission buirPermission) {
        LambdaQueryWrapper<BuirPermission> queryWrapper = initQueryWrapper(queryRequest,buirPermission);
        Page<BuirPermission> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return buirPermissionMapper.selectPage(page, queryWrapper);
    }

    /**
    * 列表的查询参数
    *
    * @author wangjunming
    * @since 2020-07-22 14:23:29
    */
    private LambdaQueryWrapper<BuirPermission> initQueryWrapper(QueryRequest queryRequest, BuirPermission buirPermission) {
        LambdaQueryWrapper<BuirPermission> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(buirPermission.getPerName())){
            queryWrapper.like(BuirPermission::getPerName,buirPermission.getPerName());
        }
        if(StringUtils.isNotBlank(buirPermission.getPerUrl())){
            queryWrapper.like(BuirPermission::getPerUrl,buirPermission.getPerUrl());
        }
        return queryWrapper;
    }

   /**
    * 保存
    *
    * @param buirPermission
    * @author wangjunming
    * @since 2020-07-22 14:23:29
    */
    @Override
    @Transactional
    public boolean saveBuirPermission(BuirPermission buirPermission) {
        //--TODO 做一些初始化动作
        return buirPermissionMapper.insert(buirPermission)>0;
    }

   /**
    * 修改
    *
    * @param buirPermission
    * @author wangjunming
    * @since 2020-07-22 14:23:29
    */
    @Override
    @Transactional
    public boolean updateBuirPermission(BuirPermission buirPermission) {
        //--TODO 做一些效验动作
        return buirPermissionMapper.updateById(buirPermission)>0;
    }

   /**
    * 获取单个
    *
    * @param buirPermission
    * @author wangjunming
    * @since 2020-07-22 14:23:29
    */
    @Override
    public BuirPermission getOneBuirPermission(BuirPermission buirPermission) {
    LambdaQueryWrapper<BuirPermission> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO 初始化查询条件
        return buirPermissionMapper.selectOne(queryWrapper);
    }

    @Override
    public List<BuirPermissionTree> getPermissionTree(BuirPermissionTree permissionTree) {
        return handlePermissionTree(permissionTree);
    }

    List<BuirPermissionTree> handlePermissionTree(BuirPermissionTree permissionTree){
        List<BuirPermissionTree> permissionTreeList = buirPermissionMapper.getPermissionTree(permissionTree);
        for (BuirPermissionTree buirPermissionTree : permissionTreeList) {
            final List<BuirPermissionTree> permissionChild = buirPermissionMapper.getPermissionTree(new BuirPermissionTree(buirPermissionTree.getId()));
            if(null != permissionChild && permissionChild.size() > 0){
                buirPermissionTree.setChildren(permissionChild);
                handlePermissionTree(new BuirPermissionTree(buirPermissionTree.getId()));
            }
        }
        return permissionTreeList;
    }



}
