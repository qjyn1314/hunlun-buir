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
    @GetMapping("/user/login_success")
    public String loginSuccess() {
//        ModelAndView modelAndView, @RequestParam(required = false) String error
//        log.info("登陆成功之后将跳转到首页!：错误码：{}",error);
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
        log.info("将跳转至登录页面!");
        return handleView();
    }

}