package com.hulunbuir.parent.tool;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/31 13:48
 */
@Slf4j
public class RequestUtils {

    /**
     * 从请求头中获取token信息
     *
     * @author wangjunming
     * @since 2020/7/13 12:57
     */
    public static String getTokenByHeader(HttpServletRequest httpServletRequest, String tokenName) {
        return httpServletRequest.getHeader(tokenName);
    }

    /**
     * 将信息设置到response请求头中
     *
     * @author wangjunming
     * @since 2020/7/13 12:57
     */
    public static void setTokenByHeader(HttpServletResponse response, String tokenKey, String tokenName) {
        response.setHeader(tokenKey, tokenName);
    }

    /**
     * 将信息从response请求头中清除
     *
     * @author wangjunming
     * @since 2020/7/13 12:57
     */
    public static void deleteHeader(HttpServletResponse response, String authTokenKey) {
        response.setHeader(authTokenKey, null);
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
//        cookie.setDomain("hulunbuir.com");
        // tomcat下多应用共享
        cookie.setPath("/");
        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
        try {
            URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("转换cookie值失败！", e);
        }
        //单位：分钟
        cookie.setMaxAge(time);
        // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
        response.addCookie(cookie);
        return response;
    }

    /**
     * 根据名字获取cookie中的token
     *
     * @author wangjunming
     * @since 2020/6/18 17:44
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        return cookieMap.getOrDefault(name, null);
    }

    /**
     * 将cookie封装到Map里面
     *
     * @author wangjunming
     * @since 2020/6/18 17:44
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
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
    public static void deleteCookieByName(HttpServletRequest request, HttpServletResponse response, String deleteKey) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        for (String key : cookieMap.keySet()) {
            if (key.equals(deleteKey)) {
                Cookie cookie = cookieMap.get(key);
                //设置cookie有效时间为0
                cookie.setMaxAge(0);
                //不设置存储路径
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

    public static void deleteUserMessageCookieAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        for (String key : cookieMap.keySet()) {
            Cookie cookie = cookieMap.get(key);
            //设置cookie有效时间为0
            cookie.setMaxAge(0);
            //不设置存储路径
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }


}
