package com.hulunbuir.clam.afternoon.persistence.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hulunbuir.clam.afternoon.persistence.entity.User;
import com.hulunbuir.clam.parent.exception.HulunBuirException;

/**
 * <p>
 * 用户表信息 服务类
 * </p>
 *
 * @author wangjunming
 * @since 2020-02-12
 */
public interface IUserService extends IService<User> {

    /**
     * 注册用户信息-添加
     *
     * @param user:
     * @author wangjunming
     * @since 2020/2/13 15:42
     */
    boolean regUser(User user) throws HulunBuirException;

    /**
     * 通过用户名称查询用户信息
     *
     * @author wangjunming
     * @since 2020/3/23 18:24
     */
    User queryUser(String usermail);
}
