package com.hulunbuir.clam.afternoon.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.parent.exception.HulunBuirException;

import java.util.HashMap;

/**
 * <p>
 * 用户信息表信息 服务类
 * </p>
 *
 * @author wangjunming
 * @since 2020-05-25
 */
public interface IBuirUserService{

    /**
     * 注册用户信息
     *
     * @author wangjunming
     * @since 2020/5/25 14:01
     */
    boolean regUser(BuirUser buirUser);

    /**
     * 验证注册用户信息以抛出异常的方式
     *
     * @author wangjunming
     * @since 2020/5/25 14:19
     */
    void validate(BuirUser buirUser,Integer type) throws HulunBuirException;


    /**
     * 根据条件查询用户信息
     *
     * @author wangjunming
     * @since 2020/5/25 15:07
     */
    BuirUser queryBuirUser(HashMap<String, Object> queryMap);


    /**
     * 查询用户分页
     *
     * @author wangjunming
     * @since 2020/6/21 22:01
     */
    IPage<BuirUser> userPage(QueryRequest queryRequest, BuirUser buirUser);

    /**
     * 编辑用户信息
     *
     * @author wangjunming
     * @since 2020/7/14 12:18
     */
    boolean userEdit(BuirUser buirUser);

    /**
     * 通过用户ID删除用户
     *
     * @author wangjunming
     * @since 2020/7/14 12:30
     */
    boolean userDel(BuirUser buirUser);

}
