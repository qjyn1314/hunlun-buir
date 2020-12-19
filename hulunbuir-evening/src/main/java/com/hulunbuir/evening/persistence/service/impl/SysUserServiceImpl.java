package com.hulunbuir.evening.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.security.util.AuthUserUtil;
import com.hulunbuir.common.base.QueryRequest;
import com.hulunbuir.evening.persistence.entity.SysUser;
import com.hulunbuir.evening.persistence.entity.SysUserRole;
import com.hulunbuir.evening.persistence.mapper.SysUserMapper;
import com.hulunbuir.evening.persistence.service.ISysUserRoleService;
import com.hulunbuir.evening.persistence.service.ISysUserService;
import com.hulunbuir.evening.persistence.vo.SysUserVo;
import com.hulunbuir.parent.exception.HulunBuirException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户表 Service实现
 *
 * @author Mr.Wang
 * @since 2020-09-18 10:33:50
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysUserRoleService service;

    /**
     * 用户表分页列表
     *
     * @param queryRequest
     * @param sysUser
     * @author Mr.Wang
     * @since 2020-09-18 10:33:50
     */
    @Override
    public IPage<SysUser> page(QueryRequest queryRequest, SysUser sysUser) {
        LambdaQueryWrapper<SysUser> queryWrapper = initQueryWrapper(queryRequest, sysUser);
        Page<SysUser> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return sysUserMapper.selectPage(page, queryWrapper);
    }

    /**
     * 列表的查询参数
     *
     * @author Mr.Wang
     * @since 2020-09-18 10:33:50
     */
    private LambdaQueryWrapper<SysUser> initQueryWrapper(QueryRequest queryRequest, SysUser sysUser) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

    /**
     * 保存
     *
     * @param sysUser
     * @author Mr.Wang
     * @since 2020-09-18 10:33:50
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SysUser sysUser) {
        sysUser.preSave();
        sysUser.setPassword(AuthUserUtil.handleUser(sysUser.getPassword()));
        boolean flag = sysUserMapper.insert(sysUser) > 0;
        flag = service.save(new SysUserRole((int) sysUser.getId().longValue(), (int) sysUser.getRoleId().longValue()));
        return flag;
    }

    /**
     * 修改
     *
     * @param sysUser
     * @author Mr.Wang
     * @since 2020-09-18 10:33:50
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(SysUser sysUser) {
        boolean flag = false;
        if(null != sysUser.getRoleId()){
            flag = service.del(sysUser.getId());
            flag = service.save(new SysUserRole((int) sysUser.getId().longValue(), (int) sysUser.getRoleId().longValue()));
        }
        flag = sysUserMapper.updateById(sysUser) > 0;
        return flag;
    }

   /**
    * 获取单个
    *
    * @param sysUser
    * @author Mr.Wang
    * @since 2020-09-18 10:33:50
    */
    @Override
    public SysUser selOne(SysUser sysUser) {
    LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName,sysUser.getUserName());
        return sysUserMapper.selectOne(queryWrapper);
    }

   /**
    * 获取单个
    *
    * @param sysUser
    * @author Mr.Wang
    * @since 2020-09-18 10:33:50
    */
    @Override
    public SysUserVo queryOne(SysUserVo sysUser) {
        return sysUserMapper.queryOne(sysUser);
    }

    /**
     * 注册用户信息
     *
     * @param username
     * @param password
     * @author wangjunming
     * @since 2020/9/18 15:35
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(String username, String password) throws HulunBuirException {
        SysUser user = new SysUser(username, password);
        if (selOne(user) != null) {
            HulunBuirException.build("该用户名已注册！");
        }
        this.save(user);
    }

    /**
     * 删除用户
     *
     * @param userId
     * @author wangjunming
     * @since 2020/9/29 16:46
     */
    @Override
    public boolean del(Long userId) {
        boolean flag = sysUserMapper.deleteById(userId) > 0;
        flag = service.del(userId);
        return flag;
    }


}
