package com.calm.security;

import com.hulunbuir.clam.common.config.RedisService;
import com.hulunbuir.clam.distributed.evening.AuthProvider;
import com.hulunbuir.clam.parent.tool.JwtUtils;
import com.hulunbuir.clam.parent.tool.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * explain: 当前登录用户的信息工具类
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/18 10:39
 */
@Slf4j
@DependsOn("applicationUtil")
@Component
public class AuthUserUtil {

    public static final String AUTH_TOKEN_KEY = "auth_token_";
    public static final int AUTH_COOKIE_TIME = 24;
    public static final int AUTH_REDIS_TIME = 60 * 60 * 12;
    private static final String AUTH_PREFIX = "Auth";

    private static PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordEncoder encoderPassword;
    private static AuthProvider userService;
    @Autowired
    private AuthProvider serviceUser;
    private static RedisService redisTemplate;
    @Autowired
    private RedisService redisHelper;

    /**
     * 初始化密码
     */
    public static String handleUser(String password) {
        return passwordEncoder.encode(password);
    }

    private static SecurityContext context() {
        return SecurityContextHolder.getContext();
    }

    private static Authentication authentication() {
        return context().getAuthentication();
    }

    private static Object principal() {
        return authentication().getPrincipal();
    }

    private static Object principal(Authentication authentication) {
        return authentication.getPrincipal();
    }

    private static UserDetails authUser() {
        return principal() instanceof UserDetails ? (UserDetails) principal() : null;
    }

    private static UserDetails authUser(Authentication authentication) {
        return principal(authentication) instanceof UserDetails ? (UserDetails) principal() : null;
    }

    private static Map<String, Object> chimesMap(CurrentUser currentUser) {
        Map<String, Object> chimes = new HashMap<>(1);
        chimes.put("authUser", currentUser);
        return chimes;
    }

    public static void logoutHandle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SecurityContextHolder.clearContext();
        RequestUtils.deleteCookieByName(request, response, AUTH_TOKEN_KEY);
        RequestUtils.deleteHeader(response, AUTH_TOKEN_KEY);
    }

    private static String getAuthToken() {
        return AUTH_PREFIX + JwtUtils.doGenerateToken(chimesMap(currentUser()));
    }

    public static String getAuthToken(CurrentUser currentUser) {
        return AUTH_PREFIX + JwtUtils.doGenerateToken(chimesMap(currentUser));
    }

    public static CurrentUser currentUser() {
        final UserDetails authUser = authUser();
        if (null == authUser || StringUtils.isBlank(authUser.getUsername())) {
            return null;
        }
        return getUserByUserName(authUser.getUsername());
    }

    public static CurrentUser successUser(Authentication authentication) {
        final UserDetails authUser = authUser(authentication);
        if (null == authUser || StringUtils.isBlank(authUser.getUsername())) {
            return null;
        }
        return getUserByUserName(authUser.getUsername());
    }

    public static CurrentUser getUserByUserName(String username) {
        log.info("当前登录用户名是：{}", username);
        return getUserByRedisUserName(username);
    }

    public static CurrentUser getUserByRedisUserName(String username) {
        final String redisUserKey = AUTH_TOKEN_KEY + username;
        final Object user = redisTemplate.getStrValue(redisUserKey);
        if (null != user) {
            return (CurrentUser) user;
        }
        final CurrentUser currentUser = new CurrentUser(userService.queryUser(username));
        redisTemplate.setStrKey(redisUserKey, currentUser, AUTH_REDIS_TIME);
        return currentUser;
    }

    @PostConstruct
    public void init() {
        passwordEncoder = encoderPassword;
        userService = serviceUser;
        redisTemplate = redisHelper;
    }

}
