package com.hulunbuir.clam.afternoon.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUserRole;
import com.hulunbuir.clam.common.base.QueryRequest;

/**
 * 用户角色关联表 Service接口
 *
 * @author wangjunming
 * @since 2020-07-23 12:24:51
 */
public interface IBuirUserRoleService {

    /**
     * 用户角色关联表分页列表
     *
     * @author wangjunming
     * @since 2020-07-23 12:24:51
     */
    IPage<BuirUserRole> buirUserRolePage(QueryRequest queryRequest, BuirUserRole buirUserRole);

    /**
     * 保存
     *
     * @author wangjunming
     * @since 2020-07-23 12:24:51
     */
     boolean saveBuirUserRole(BuirUserRole buirUserRole);

    /**
     * 修改
     *
     * @author wangjunming
     * @since 2020-07-23 12:24:51
     */
     boolean updateBuirUserRole(BuirUserRole buirUserRole);


    /**
     * 获取单个
     *
     * @author wangjunming
     * @since 2020-07-23 12:24:51
     */
    BuirUserRole getOneBuirUserRole(BuirUserRole buirUserRole);


    /**
     * 删除用户与角色中间表
     *
     * @author wangjunming
     * @since 2020/7/23 16:22
     */
    boolean delUserRole(Integer userId);

}
