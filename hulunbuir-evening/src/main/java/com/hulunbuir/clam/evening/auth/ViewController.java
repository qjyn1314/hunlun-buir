package com.hulunbuir.clam.evening.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/28 11:31
 */
@Slf4j
@Controller
public class ViewController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping({"/"})
    public String index() {
        return verifyUser(Constant.INDEX);
    }

    @GetMapping({"/page/**.html", "/page/*/**.html","/register/**","/login/**"})
    public String initView() {
        final String uri = request.getRequestURI();
        log.info("进入了跳转页面接口，进行跳转页面：{}", uri);
        return verifyUser(uri.replace(".html", ""));
    }

    public String verifyUser(String viewUrl) {
        final boolean login = AuthService.me().isLogin(request);
        return login ? viewUrl : Constant.LOGIN_URL;
    }

    final static class Constant {
        public static final String INDEX = "index";
        public static final String LOGIN_URL = "login/login";
        public static final String HTML_SUFFIX = ".html";
        public static final String PAGE = "page";
    }









}

