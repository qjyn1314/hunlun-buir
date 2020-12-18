package com.hulunbuir.evening.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.calm.security.support.Auth;
import com.hulunbuir.common.base.QueryRequest;
import com.hulunbuir.common.config.RedisService;
import com.hulunbuir.evening.persistence.entity.SysPermission;
import com.hulunbuir.evening.persistence.mapper.SysPermissionMapper;
import com.hulunbuir.evening.persistence.service.ISysPermissionService;
import com.hulunbuir.evening.persistence.vo.LayPermissionTree;
import com.hulunbuir.evening.persistence.vo.SysPermissionTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    private RedisService redisService;

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
        LambdaQueryWrapper<SysPermission> queryWrapper = initQueryWrapper(queryRequest, sysPermission);
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
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SysPermission sysPermission) {
        redisService.deleteByKey(RedisService.PERMISSION_DELKEY);
        //--TODO 做一些初始化动作
        return sysPermissionMapper.insert(sysPermission) > 0;
    }

    /**
     * 修改
     *
     * @param sysPermission
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(SysPermission sysPermission) {
        redisService.deleteByKey(RedisService.PERMISSION_DELKEY);
        //--TODO 做一些效验动作
        return sysPermissionMapper.updateById(sysPermission) > 0;
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

    /**
     * 当前登录用户的权限列表
     *
     * @return
     * @author wangjunming
     * @since 2020/9/25 18:00
     */
    @Override
    public List<SysPermissionTree> permissionTree(SysPermissionTree permissionTree, Integer userId) {
        final Object permission = redisService.getStrValue(RedisService.PERMISSION + userId);
        if (null != permission) {
            return (List<SysPermissionTree>)permission;
        } else {
            final List<SysPermissionTree> permissionTrees = handlePermissionTree(permissionTree, userId);
            redisService.setStrKey(RedisService.PERMISSION + userId, permissionTrees, Auth.day);
            return permissionTrees;
        }
    }

    /**
     * 添加权限页面的权限树
     *
     * @author wangjunming
     * @since 2020/9/27 15:54
     */
    @Override
    public List<LayPermissionTree> layPermissionTree(LayPermissionTree layPermissionTree) {
        return handleLayPermissionTree(layPermissionTree);
    }

    List<LayPermissionTree> handleLayPermissionTree(LayPermissionTree permissionTree) {
        List<LayPermissionTree> permissionTreeList = sysPermissionMapper.getLayPermissionTree(permissionTree);
        for (LayPermissionTree layPermissionTree : permissionTreeList) {
            final List<LayPermissionTree> permissionChild = sysPermissionMapper.getLayPermissionTree(new LayPermissionTree(layPermissionTree.getId()));
            if (null != permissionChild && permissionChild.size() > 0) {
                layPermissionTree.setChildren(permissionChild);
                handleLayPermissionTree(new LayPermissionTree(layPermissionTree.getId()));
            }
        }
        return permissionTreeList;
    }

    List<SysPermissionTree> handlePermissionTree(SysPermissionTree permissionTree, Integer userId) {
        List<SysPermissionTree> permissionTreeList = sysPermissionMapper.getPermissionTree(permissionTree, userId);
        for (SysPermissionTree buirPermissionTree : permissionTreeList) {
            final List<SysPermissionTree> permissionChild = sysPermissionMapper.getPermissionTree(new SysPermissionTree(buirPermissionTree.getId()), userId);
            if (null != permissionChild && permissionChild.size() > 0) {
                buirPermissionTree.setChildren(permissionChild);
                handlePermissionTree(new SysPermissionTree(buirPermissionTree.getId()), userId);
            }
        }
        return permissionTreeList;
    }

}
