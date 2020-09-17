package com.calm.security.config;

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

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("当前登录用户是{}",userName);
        //模拟登录用户
        return User.withUsername("admin").password(passwordEncoder.encode("admin")).authorities("po").build();
    }

}
