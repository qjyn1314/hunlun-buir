package com.hulunbuir.clam.afternoon.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirRole;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.parent.exception.HulunBuirException;

import java.util.List;

/**
 * 角色表 Service接口
 *
 * @author wangjunming
 * @since 2020-07-23 10:09:33
 */
public interface IBuirRoleService {

    /**
     * 角色表分页列表
     *
     * @author wangjunming
     * @since 2020-07-23 10:09:33
     */
    IPage<BuirRole> buirRolePage(QueryRequest queryRequest, BuirRole buirRole);

    /**
     * 保存
     *
     * @author wangjunming
     * @since 2020-07-23 10:09:33
     */
     boolean saveBuirRole(BuirRole buirRole);

    /**
     * 修改
     *
     * @author wangjunming
     * @since 2020-07-23 10:09:33
     */
     boolean updateBuirRole(BuirRole buirRole) throws HulunBuirException;


    /**
     * 获取单个
     *
     * @author wangjunming
     * @since 2020-07-23 10:09:33
     */
    BuirRole getOneBuirRole(BuirRole buirRole);

    /**
     * 获取列表
     *
     * @author wangjunming
     * @since 2020/7/23 11:15
     */
    List<BuirRole> findInfoList(BuirRole buirRole);
}
