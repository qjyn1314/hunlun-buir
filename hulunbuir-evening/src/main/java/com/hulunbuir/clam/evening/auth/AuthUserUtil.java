package com.hulunbuir.clam.evening.auth;

import com.hulunbuir.clam.evening.persistence.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>
 * explain: 当前登录用户的信息工具类
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/18 10:39
 */
@DependsOn("applicationContextUtils")
@Component
public class AuthUserUtil {

    private static PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordEncoder encoderPassword;

    /**
     * 初始化密码
     *
     * @author wangjunming
     * @since 2020/9/18 15:43
     */
    public static SysUser handleUser(SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @PostConstruct
    public void init(){
        passwordEncoder = encoderPassword;
    }

    private static SecurityContext context(){
        return SecurityContextHolder.getContext();
    }

    private static Authentication authentication(){
        return context().getAuthentication();
    }

    private static Object principal(){
        return authentication().getPrincipal();
    }

    public static Object authUser(){
        return principal();
    }


}
