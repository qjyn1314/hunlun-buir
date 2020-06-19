package com.hulunbuir.clam.route.config.jwt;

import com.hulunbuir.clam.distributed.model.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * explain:获取当前登录用户信息
 * </p>
 *
 * @author wangjunming
 * @since 2020/6/18 16:50
 */
@Slf4j
@Component
public class CurrentUser {

    //用户的cookie名称
    private static final String USER_COOKIE = "user_token";
    //七天
    private static final int USER_COOKIE_TIME_OUT = 60 * 24 * 7;
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
        final String userInfoToken = JWT_TOKEN_UTIL.doUserInfoToken(user);
        return setCookie(response, USER_COOKIE, userInfoToken, USER_COOKIE_TIME_OUT);
    }

    /**
     * 获取用户user的cookie
     *
     * @author wangjunming
     * @since 2020/6/18 17:23
     */
    public static UserManager getUserMessageByCookie(HttpServletRequest request) {
        final Cookie userCookie = getCookieByName(request, USER_COOKIE);
        assert userCookie != null;
        final String userTokenCookie = userCookie.getValue();
        return JWT_TOKEN_UTIL.getUserInfoByToken(userTokenCookie);
    }

    /**
     * 删除用户user的cookie
     *
     * @author wangjunming
     * @since 2020/6/18 17:23
     */
    public static HttpServletResponse deleteUserMessageCookie(HttpServletRequest request, HttpServletResponse response) {
        deleteCookieByName(request, response, USER_COOKIE);
        return response;
    }

    /**
     * 存储信息到cookie
     *
     * @author wangjunming
     * @since 2020/6/18 17:23
     */
    public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value, int time) {
        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie(name, value);
        // tomcat下多应用共享
        cookie.setPath("/");
        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
        try {
            URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //单位：分钟
        cookie.setMaxAge(time);
        // 将Cookie添加到Response中,使之生效
        response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
        return response;
    }

    /**
     * 根据名字获取cookie
     *
     * @author wangjunming
     * @since 2020/6/18 17:44
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        return cookieMap.getOrDefault(name, null);
    }

    /**
     * 将cookie封装到Map里面
     *
     * @author wangjunming
     * @since 2020/6/18 17:44
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 删除cookie
     *
     * @author wangjunming
     * @since 2020/6/18 17:42
     */
    private static void deleteCookieByName(HttpServletRequest request, HttpServletResponse response, String deleteKey) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        for (String key : cookieMap.keySet()) {
            if (key.equals(deleteKey)) {
                Cookie cookie = cookieMap.get(key);
                cookie.setMaxAge(0);//设置cookie有效时间为0
                cookie.setPath("/");//不设置存储路径
                response.addCookie(cookie);
            }
        }
    }

    @PostConstruct
    public void init() {
        JWT_TOKEN_UTIL = jwtTokenUtil;
    }

}
