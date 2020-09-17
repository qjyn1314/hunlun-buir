package com.calm.security;

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
    String HTML_SUFFIX = ".html";

}
