package com.calm.security.config;

import com.hulunbuir.clam.distributed.evening.AuthProvider;
import com.hulunbuir.clam.distributed.evening.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain: 自定义用户信息
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/17 16:30
 */
@Slf4j
@Component
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthProvider authProvider;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("当前进行登录的用户名是：{}", userName);
        final AuthUser authUser = authProvider.queryUser(userName);
        if (null == authUser) {
            return null;
        }
        return User.withUsername(authUser.getUserName()).password(authUser.getPassword()).authorities("po").build();
    }

}
