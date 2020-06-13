package com.hulunbuir.clam.afternoon.login;

import com.hulunbuir.clam.common.config.submit.NoRepeatSubmit;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import com.hulunbuir.clam.parent.result.JsonResult;
import com.hulunbuir.clam.route.config.shiro.ForbiddenUserException;
import com.hulunbuir.clam.route.config.shiro.IncorrectCaptchaException;
import com.hulunbuir.clam.route.config.shiro.NoUserException;
import com.hulunbuir.clam.route.config.shiro.ShiroTool;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;

/**
 * <p>
 * Explain:用于跳转页面使用的controller，通过访问接口的形式返回得到固定的页面
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-12 10:34
 */
@Controller
@Slf4j
public class LoginController {
    /**
     * 直接访问首页页面
     *
     * @author wangjunming
     * @since 2020/2/12 11:54
     */
    @GetMapping("/")
    public String index() {
        if (ShiroTool.isAuthenticated()) {
            return "redirect:/console.html";
        }
        return "buir/login";
//        return "index";
    }

    /**
     * 用于首页的提交信息
     *
     * @author wangjunming
     * @since 2020/2/12 11:54
     */
    @PostMapping("/")
    @ResponseBody
    @NoRepeatSubmit
    public JsonResult indexSubmit(String contactName, String contactEmail, String contactMessage) {
        log.info("首页所提交的联系信息是：contactName：{},contactEmail：{},contactMessage：{}", contactName, contactEmail, contactMessage);
        if (StringUtils.isNotBlank(contactName) && StringUtils.isNotBlank(contactEmail) && StringUtils.isNotBlank(contactMessage)) {
            return JsonResult.successMsg("恭喜您提交成功！！ 我们将很快会的将注册账号给您发送至所提交的邮箱中！！");
        }
        return JsonResult.success();
    }

    /**
     * 用于登陆成功之后的跳转页面
     *
     * @author wangjunming
     * @since 2020/2/12 11:54
     */
    @GetMapping("/console.html")
    public String consoleHtml() {
        return "/buir/console";
    }

    /**
     * 用于首页的跳转到登录页面
     *
     * @author wangjunming
     * @since 2020/2/12 11:54
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroTool.isAuthenticated()) {
            return "redirect:/console.html";
        }
        return "buir/login";
    }

    /**
     * 用户登录
     *
     * @author wangjunming
     * @since 2020/3/3 13:07
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public String login(HttpServletRequest request) throws HulunBuirException {
        log.info("用户登录前端传参：{}", request);
        //登录失败从request中获取shiro处理的异常信息。shiroLoginFailure:就是shiro异常类的全类名.
        Enumeration<String> attributeNames = request.getAttributeNames();
        log.info("用户登录中的HttpServletRequest所属变量名称：{}", Collections.singletonList(attributeNames));
        Object exception = request.getAttribute("shiroLoginFailure");
        log.error("用户登录异常：{}", exception);
        if (exception != null) {
            if (exception instanceof UnknownAccountException) {
                HulunBuirException.build("用户名不正确，请重新输入");
            } else if (exception instanceof IncorrectCredentialsException) {
                HulunBuirException.build("密码错误，请重新输入");
            } else if (exception instanceof IncorrectCaptchaException) {
                HulunBuirException.build("验证码已过期");
            } else if (exception instanceof ForbiddenUserException) {
                HulunBuirException.build("该用户已被禁用，如有疑问请联系系统管理员");
            } else if(exception instanceof NoUserException){
                HulunBuirException.build("请您注册该账号");
            } else {
                HulunBuirException.build("发生未知错误，请联系管理员");
            }
            return "/buir/login";
        }
        return "redirect:/console.html";
    }

}
