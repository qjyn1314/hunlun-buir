package com.hulunbuir.clam.afternoon.persistence.service;

import com.hulunbuir.clam.afternoon.persistence.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @author wangjunming
     * @since 2020/2/13 15:42
     * @param user:
     * @return java.lang.Integer
     */
    boolean regUser(User user);


}
