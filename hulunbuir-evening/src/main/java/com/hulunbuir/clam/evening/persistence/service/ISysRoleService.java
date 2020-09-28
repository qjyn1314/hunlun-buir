package com.hulunbuir.clam.evening.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.SysRole;
import com.hulunbuir.clam.evening.persistence.vo.SysRoleVo;

/**
 * 角色表 Service接口
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
public interface ISysRoleService {

    /**
     * 角色表分页列表
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    IPage<SysRole> page(QueryRequest queryRequest, SysRole sysRole);

    /**
     * 保存
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
     boolean save(SysRoleVo sysRole);

    /**
     * 修改
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
     boolean update(SysRoleVo sysRole);


    /**
     * 获取单个
     *
     * @author Mr.Wang
     * @since 2020-09-22 11:04:50
     */
    SysRoleVo selOne(SysRole sysRole);


}
