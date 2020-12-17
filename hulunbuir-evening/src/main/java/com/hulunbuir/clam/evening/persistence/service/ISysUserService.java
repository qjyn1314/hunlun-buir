package com.hulunbuir.clam.evening.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.SysUser;
import com.hulunbuir.clam.evening.persistence.vo.SysUserVo;
import com.hulunbuir.clam.parent.exception.HulunBuirException;

/**
 * 用户表 Service接口
 *
 * @author Mr.Wang
 * @since 2020-09-18 10:33:50
 */
public interface ISysUserService {

    /**
     * 用户表分页列表
     *
     * @author Mr.Wang
     * @since 2020-09-18 10:33:50
     */
    IPage<SysUser> page(QueryRequest queryRequest, SysUser sysUser);

    /**
     * 保存
     *
     * @author Mr.Wang
     * @since 2020-09-18 10:33:50
     */
     boolean save(SysUser sysUser);

    /**
     * 修改
     *
     * @author Mr.Wang
     * @since 2020-09-18 10:33:50
     */
     boolean update(SysUser sysUser);


    /**
     * 获取单个
     *
     * @author Mr.Wang
     * @since 2020-09-18 10:33:50
     */
    SysUser selOne(SysUser sysUser);


    /**
     * 获取单个
     *
     * @author wangjunming
     * @since 2020/9/30 17:12
     */
    SysUserVo queryOne(SysUserVo sysUser);


    /**
     * 注册用户信息
     *
     * @author wangjunming
     * @since 2020/9/18 15:35
     */
    void registerUser(String username, String password) throws HulunBuirException;


    /**
     * 删除用户
     *
     * @author wangjunming
     * @since 2020/9/29 16:46
     */
    boolean del(Long userId);

}
