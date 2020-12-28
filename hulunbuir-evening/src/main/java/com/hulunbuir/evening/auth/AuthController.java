package com.hulunbuir.evening.auth;

import com.hulunbuir.security.support.AuthService;
import com.hulunbuir.security.util.AuthUserUtil;
import com.hulunbuir.parent.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @GetMapping({"/login_success", "/"})
    public String loginSuccessGet() {
        if (AuthUserUtil.isAuthenticated()) {
            log.info("登陆成功之后将跳转到首页!");
            return INDEX;
        }
        log.info("未登录则访问登录页面!");
        return "redirect:/auth/login";
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
    public ModelAndView failLogin(ModelAndView modelAndView) {
        log.info("将跳转至认证失败页面!");
        modelAndView.setViewName(handleView());
        modelAndView.addObject("error", "error");
        return modelAndView;
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

    /**
     * 获取当前登录用户的接口
     *
     * @author wangjunming
     * @since 2020/9/18 10:38
     */
    @ResponseBody
    @GetMapping("/userInfo")
    public JsonResult userInfo() {
        return JsonResult.success(AuthUserUtil.currentUser());
    }

}