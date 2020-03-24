package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hulunbuir.clam.afternoon.config.shiro.AfternoonShiroUtil;
import com.hulunbuir.clam.afternoon.persistence.entity.User;
import com.hulunbuir.clam.afternoon.persistence.mapper.UserMapper;
import com.hulunbuir.clam.afternoon.persistence.service.IUserService;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
     * @author wangjunming
     * @since 2020/2/13 15:42
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean regUser(User user) throws HulunBuirException {
        if (null != queryUser(user.getUserMail())) {
            throw HulunBuirException.build("已有用户名!");
        }
        user.setPasswordSalt(AfternoonShiroUtil.randomSalt());
        user.setUserPassword(AfternoonShiroUtil.encryptPassword(user.getUserMail(), user.getUserPassword(), user.getPasswordSalt()));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return user.insert();
    }

    @Override
    public User queryUser(String usermail) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserMail, usermail);
        return this.getOne(queryWrapper);
    }
}
