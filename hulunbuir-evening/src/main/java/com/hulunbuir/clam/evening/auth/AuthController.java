package com.hulunbuir.clam.evening.auth;

import com.calm.security.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * explain: 用户登录验证控制层
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/16 10:16
 */
@Slf4j
@Controller
public class AuthController extends AuthService {

    /**
     * 登陆成功后重定向的请求
     *
     * @author wangjunming
     * @since 2020/9/16 10:47
     */
    @GetMapping("/login_success")
    public String loginSuccessGet() {
        log.info("登陆成功之后将跳转到首页!");
        return INDEX;
    }

    /**
     * 将跳转至登录页面，让用户登录
     *
     * @author wangjunming
     * @since 2020/9/16 10:47
     */
    @GetMapping("/auth/login")
    public String authLogin() {
        log.info("将跳转至登录认证页面!");
        return handleView();
    }

    /**
     * 将跳转至登录页面，让用户登录
     *
     * @author wangjunming
     * @since 2020/9/16 10:47
     */
    @GetMapping("/auth/fail")
    public String failLogin() {
        log.info("将跳转至认证失败页面!");
        return handleView();
    }

    /**
     * 根据路径跳转到相应的界面
     *
     * @author wangjunming
     * @since 2020/9/18 10:38
     */
    @GetMapping({"/page/**.html", "/page/*/**.html","/auth/register"})
    public String initView() {
        return handleView();
    }

}