package com.hulunbuir.clam.evening.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.SysRole;
import com.hulunbuir.clam.evening.persistence.entity.SysRolePermission;
import com.hulunbuir.clam.evening.persistence.mapper.SysRoleMapper;
import com.hulunbuir.clam.evening.persistence.service.ISysRolePermissionService;
import com.hulunbuir.clam.evening.persistence.service.ISysRoleService;
import com.hulunbuir.clam.evening.persistence.vo.SysRoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    private ISysRolePermissionService rolePermissionService;

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
        LambdaQueryWrapper<SysRole> queryWrapper = initQueryWrapper(queryRequest, sysRole);
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
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SysRoleVo sysRole) {
        boolean roleFlag = sysRoleMapper.insert(sysRole) > 0;
        return savePermission(sysRole, roleFlag);
    }

    /**
     * 修改
     *
     * @param sysRole
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(SysRoleVo sysRole) {
        boolean flag = rolePermissionService.del(sysRole.getId());
        flag = sysRoleMapper.updateById(sysRole) > 0;
        return savePermission(sysRole, flag);
    }

    private boolean savePermission(SysRoleVo sysRole, boolean flag) {
        if (StringUtils.isNotBlank(sysRole.getPermission())) {
            final String[] permissionId = sysRole.getPermission().split(",");
            for (String id : permissionId) {
                SysRolePermission rolePermission = new SysRolePermission(sysRole.getId(), Integer.valueOf(id));
                flag = rolePermissionService.save(rolePermission);
            }
        }
        return flag;
    }

    /**
     * 获取单个
     *
     * @param sysRole
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    @Override
    public SysRoleVo selOne(SysRole sysRole) {
        //--TODO 初始化查询条件
        return sysRoleMapper.selOne(sysRole);
    }

    /**
     * 查询的角色列表
     *
     * @param sysRole
     * @author wangjunming
     * @since 2020/9/28 11:31
     */
    @Override
    public List<SysRole> list(SysRole sysRole) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        return sysRoleMapper.selectList(queryWrapper);
    }


}
