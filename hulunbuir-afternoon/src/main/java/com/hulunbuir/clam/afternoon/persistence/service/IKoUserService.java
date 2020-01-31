package com.hulunbuir.clam.afternoon.persistence.service;

import com.hulunbuir.clam.afternoon.persistence.entity.KoUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wangjunming
 * @since 2020-01-17
 */
public interface IKoUserService extends IService<KoUser> {

    /**
     * 添加用户信息，测试分布式事务
     * @author wangjunming
     * @since 2020/1/18 12:04
     * @param user:
     * @return boolean
     */
    boolean insertUserGlob(KoUser user);

    /**
     * 添加用户信息，测试spring事务
     * @author wangjunming
     * @since 2020/1/18 12:04
     * @param user:
     * @return boolean
     */
    boolean insertUser(KoUser user);

}
