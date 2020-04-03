package com.hulunbuir.clam.afternoon.controller;

import com.hulunbuir.clam.common.config.submit.NoRepeatSubmit;
import com.hulunbuir.clam.distributed.admin.AdminMailProvider;
import com.hulunbuir.clam.parent.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class ViewController {

    @Reference(check = false)
    private AdminMailProvider mailService;

    /**
     * 直接访问首页页面
     *
     * @author wangjunming
     * @since 2020/2/12 11:54
     */
    @GetMapping("/")
    public String index() {
        return "index";
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
     * 用于首页的跳转到登录页面
     *
     * @author wangjunming
     * @since 2020/2/12 11:54
     */
    @GetMapping("/login.html")
    public String loginHtml() {
        return "/buir/login";
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


}
