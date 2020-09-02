package com.hulunbuir.clam.evening.auth;

import com.hulunbuir.clam.parent.exception.HulunBuirException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping({"/page/**.html", "/page/*/**.html","/register/**","/login/**"})
    public String initView() {
        return verifyUser(request.getRequestURI().replace(Constant.HTML_SUFFIX, ""));
    }

    @Autowired
    private HttpServletRequest request;

    @GetMapping({"/"})
    public String index() {
        return verifyUser(Constant.INDEX);
    }

    public String verifyUser(String viewUrl) {
        return AuthService.me().isLogin(request) ? viewUrl : Constant.REGISTER_URL.equals(viewUrl) ? Constant.REGISTER_VIEW_URL : Constant.LOGIN_URL;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password) throws HulunBuirException {
        AuthService.me().login(username,password);
        return verifyUser(Constant.INDEX);
    }

    final static class Constant {
        public static final String INDEX = "index";
        public static final String LOGIN_URL = "login/login";
        public static final String REGISTER_VIEW_URL = "register/register";
        public static final String REGISTER_URL = "/register/register";
        public static final String HTML_SUFFIX = ".html";
    }

}

