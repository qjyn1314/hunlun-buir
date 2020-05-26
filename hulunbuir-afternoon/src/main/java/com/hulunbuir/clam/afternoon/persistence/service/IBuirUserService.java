package com.hulunbuir.clam.afternoon.persistence.service;

import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface IBuirUserService extends IService<BuirUser> {

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
    void validate(BuirUser buirUser) throws HulunBuirException;


    /**
     * 根据条件查询用户信息
     *
     * @author wangjunming
     * @since 2020/5/25 15:07
     */
    BuirUser queryBuirUser(HashMap<String, Object> queryMap);


}
