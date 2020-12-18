package com.hulunbuir.security.support;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/15 17:57
 */
public interface Auth {
    String INDEX = "index";
    String LOGIN_URL = "/auth/login";
    String LOGIN_SUCCESS_URL = "/login_success";
    String LOGIN_FORM_URL = "/login/form";
    String LOGIN_FAIL_URL = "/auth/fail";
    String LOGOUT_URL = "/logout";
    String HTML_SUFFIX = ".html";
    //1分
    long points = 60*60 ;
    //1时
    long when = 60*60*60;
    //1天
    long day = 60*60*60*24;
    //1周
    long week = 60*60*60*24*7;
    //1月
    long month = 60*60*60*24*30;
}
