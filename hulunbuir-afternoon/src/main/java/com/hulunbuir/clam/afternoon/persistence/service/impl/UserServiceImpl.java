package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.hulunbuir.clam.afternoon.persistence.entity.User;
import com.hulunbuir.clam.afternoon.persistence.mapper.UserMapper;
import com.hulunbuir.clam.afternoon.persistence.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表信息 服务实现类
 * </p>
 *
 * @author wangjunming
 * @since 2020-02-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 注册用户信息-添加
     *
     * @param user :
     * @return java.lang.Integer
     * @author wangjunming
     * @since 2020/2/13 15:42
     */
    @Override
    @Transactional
    public boolean regUser(User user) {

        return user.insert();
    }
}
