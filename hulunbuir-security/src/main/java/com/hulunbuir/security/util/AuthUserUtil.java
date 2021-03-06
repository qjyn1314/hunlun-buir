package com.hulunbuir.security.util;

import com.hulunbuir.common.config.RedisService;
import com.hulunbuir.distributed.evening.AuthProvider;
import com.hulunbuir.distributed.evening.AuthUser;
import com.hulunbuir.parent.exception.HulunBuirException;
import com.hulunbuir.parent.tool.JwtUtils;
import com.hulunbuir.parent.tool.RequestUtils;
import com.hulunbuir.security.config.CurrentUser;
import io.jsonwebtoken.Claims;
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

    public static final String AUTH_TOKEN_KEY = "AuthToken_";
    public static final int AUTH_COOKIE_TIME = 60 * 60 * 24;
    public static final int AUTH_REDIS_TIME = 60 * 60 * 12;
    public static final String AUTH_PREFIX = "Auth_";
    /**
     * 签发jwt-token中map的key
     */
    private static final String CHIMES_MAP_KEY = "authUser";
    /**
     * 操作密码
     */
    private static PasswordEncoder passwordEncoder;
    /**
     * 查询用户信息
     */
    private static AuthProvider userService;
    /**
     * 操作redis数据库
     */
    private static RedisService redisTemplate;
    @Autowired
    private PasswordEncoder encoderPassword;
    @Autowired
    private AuthProvider serviceUser;
    @Autowired
    private RedisService redisService;

    /**
     * 将当前登录用户信息存储到map中用于签发jwt-token
     *
     * @param currentUser 当前系统的登录用户
     * @return Map
     */
    private static Map<String, Object> chimesMap(CurrentUser currentUser) {
        Map<String, Object> chimes = new HashMap<>(1);
        chimes.put(CHIMES_MAP_KEY, currentUser);
        return chimes;
    }

    /**
     * 初始化密码
     */
    public static String handleUser(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * 获取Security全局信息
     */
    private static SecurityContext context() {
        return SecurityContextHolder.getContext();
    }

    /**
     * 被认证的主体的身份
     */
    private static Authentication authentication() {
        return context().getAuthentication();
    }

    /**
     * 当前security中的登录用户信息
     */
    private static Object principal() {
        return authentication().getPrincipal();
    }

    /**
     * 是否一登录
     */
    public static Boolean isAuthenticated() {
        return authentication().isAuthenticated();
    }

    /**
     * 当前登录用户信息
     */
    private static CurrentUser authUser() {
        return principal() instanceof UserDetails ? (CurrentUser) principal() : null;
    }

    /**
     * 获取当前登录用户的详细信息
     * <p>
     * 为其他模块调用的静态方法，获取当前登录用户信息
     *
     * @author wangjunming
     * @since 2020/11/28 22:06
     */
    public static CurrentUser currentUser() {
        final CurrentUser authUser = authUser();
        if (null == authUser) {
            HulunBuirException.build("当前登录用户信息过期，请重新登录。");
        }
        authUser.setPassword(null);
        return authUser;
    }

    /**
     * 将当前登录用户信息转换成一个jwt-token，并存储到redis中，和cookie中
     */
    private static String getAuthToken(HttpServletResponse response, Authentication authentication) {
        log.info("SecurityContextHolder.getContext().getAuthentication()与登录成功处理器中的[Authentication authentication]是否一致：{}", authentication().equals(authentication));
        CurrentUser currentUser = getUserBySecurityAndDb();
        String authToken = AUTH_PREFIX + JwtUtils.doGenerateToken(chimesMap(currentUser));
        RequestUtils.setCookie(response, AUTH_TOKEN_KEY, authToken, AUTH_COOKIE_TIME);
        String redisTokenKey = AUTH_TOKEN_KEY + currentUser.getUserName();
        redisTemplate.setStrKey(redisTokenKey, authToken, AUTH_REDIS_TIME);
        return authToken;
    }

    /**
     * 通过security获取当前登录用户名，查询用户详细信息
     *
     * @author wangjunming
     * @since 2020/11/29 10:43
     */
    private static CurrentUser getUserBySecurityAndDb() {
        final UserDetails authUser = authUser();
        if (null == authUser || StringUtils.isBlank(authUser.getUsername())) {
            return null;
        }
        final String username = authUser.getUsername();
        log.info("当前登录成功的用户名是：{}", username);
        return getCurrentUserByDb(username);
    }

    /**
     * 从数据库中获取用户信息
     *
     * @author wangjunming
     * @since 2020/11/28 22:35
     */
    private static CurrentUser getCurrentUserByDb(String username) {
        AuthUser authUser = userService.queryUser(username);
        CurrentUser currentUser = new CurrentUser();
        currentUser.preUser(authUser);
        return currentUser;
    }

    /**
     * 退出登录的操作
     *
     * @author wangjunming
     * @since 2020/11/28 22:03
     */
    public static void logoutHandle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        RequestUtils.deleteCookieByName(request, response, AUTH_TOKEN_KEY);
    }

    /**
     * 根据jwt-token解析出用户信息
     *
     * @author wangjunming
     * @since 2020/11/29 11:17
     */
    private static CurrentUser getUserByToken(String authToken) {
        authToken = authToken.substring(AUTH_PREFIX.length());
        Claims claimFromToken = null;
        try {
            claimFromToken = JwtUtils.getClaimFromToken(authToken);
        } catch (Exception e) {
            log.error("解析token失败！！", e);
            return null;
        }
        final HashMap hashMap = claimFromToken.get(CHIMES_MAP_KEY, HashMap.class);
        return JwtUtils.mapToEntity(hashMap, CurrentUser.class);
    }

    /**
     * 初始化信息，即在此对象初始化成功之后赋值给静态资源一些信息
     *
     * @author wangjunming
     * @since 2020/11/28 22:08
     */
    @PostConstruct
    public void init() {
        passwordEncoder = encoderPassword;
        userService = serviceUser;
        redisTemplate = redisService;
    }

}
