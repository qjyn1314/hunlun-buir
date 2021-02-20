package com.hulunbuir.security.config;

import com.hulunbuir.security.exception.NotActivationException;
import com.hulunbuir.distributed.evening.AuthProvider;
import com.hulunbuir.distributed.evening.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain: 自定义用户信息，将从数据库中查询
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/17 16:30
 */
@Slf4j
@Component
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private AuthProvider authProvider;

    @Override
    public UserDetails loadUserByUsername(String userName) throws AuthenticationException {
        log.info("当前进行登录的用户名是：{}", userName);
        final AuthUser authUser = authProvider.queryUser(userName);
        if (null == authUser) {
            throw new UsernameNotFoundException("未找到您的用户名，请注册！");
        }
        if (AuthUser.UserStatus.STATUS_0.equals(authUser.getStatus())) {
            throw new NotActivationException("您的账号未激活，请联系管理员，qjyn1314@163.com");
        }
        CurrentUser currentUser = new CurrentUser();
        currentUser.preUser(authUser);
        return currentUser;
    }

}
