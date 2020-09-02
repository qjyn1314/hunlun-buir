package com.hulunbuir.clam.route.config.jwt;

import com.hulunbuir.clam.common.config.ApplicationContextUtils;
import com.hulunbuir.clam.parent.tool.RequestUtils;
import com.hulunbuir.clam.distributed.model.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * explain: 获取当前浏览器中的登录用户cookie信息
 * </p>
 *
 * @author wangjunming
 * @since 2020/6/18 16:50
 */
@Slf4j
@Component
public class CurrentUser {

    /**
     * 用户的token存在于cookie中名称，
     */
    public static final String USER_COOKIE = "user_token";
    public static final String USER_REMEMBER_ME = "user_remember_me";
    /**
     * 30 天
     */
    public static final int USER_COOKIE_TIME_OUT = 60 * 60 * 24 * 30;
    private static JwtTokenUtil JWT_TOKEN_UTIL;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 存储用户user的cookie
     *
     * @author wangjunming
     * @since 2020/6/18 17:23
     */
    public static HttpServletResponse handleUserMessage(UserManager user, HttpServletResponse response) {
        return RequestUtils.setCookie(response, USER_COOKIE, JWT_TOKEN_UTIL.doUserInfoToken(user), USER_COOKIE_TIME_OUT);
    }

    /**
     * 获取用户user的cookie
     *
     * @author wangjunming
     * @since 2020/6/18 17:23
     */
    private static UserManager getUserMessageByCookie(HttpServletRequest request) {
        final Cookie userCookie = RequestUtils.getCookieByName(request, USER_COOKIE);
        final String userTokenCookie = userCookie.getValue();
        return JWT_TOKEN_UTIL.getUserInfoByToken(userTokenCookie);
    }

    /**
     * 从当前登录浏览器中获取当前登录用户
     *
     * @author wangjunming
     * @since 2020/6/18 17:23
     */
    public static UserManager getUserMessage() {
        UserManager userManager = JWT_TOKEN_UTIL.getUserInfoByToken(RequestUtils.getTokenByHeader(ApplicationContextUtils.getHttpServletRequest(),USER_COOKIE));
        if(null != userManager){
            return userManager;
        }
        return JWT_TOKEN_UTIL.getUserInfoByToken(RequestUtils.getCookieByName(ApplicationContextUtils.getHttpServletRequest(), USER_COOKIE).getValue());
    }

    /**
     * 删除用户user的cookie
     *
     * @author wangjunming
     * @since 2020/6/18 17:23
     */
    public static void deleteUserMessageCookie(HttpServletRequest request, HttpServletResponse response) {
        RequestUtils.deleteCookieByName(request, response, USER_COOKIE);
    }

    @PostConstruct
    public void init() {
        JWT_TOKEN_UTIL = jwtTokenUtil;
    }

}
