package com.hulunbuir.clam.afternoon.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hulunbuir.clam.common.mail.MailConstants;
import com.hulunbuir.clam.distributed.admin.AdminMailProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 直接访问登录页面
     *
     * @return java.lang.String
     * @author wangjunming
     * @since 2020/2/12 11:54
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 用于提交信息
     *
     * @return java.lang.String
     * @author wangjunming
     * @since 2020/2/12 11:54
     */
    @PostMapping("/")
    @ResponseBody
    public String indexSubmit(String contactName,String contactEmail,String contactMessage) {
        log.info("首页所提交的联系信息是：contactName-{},contactEmail-{},contactMessage-{}",contactName,contactEmail,contactMessage);
        return "<script>alert('你太帅了，一添加就添加成功了！');</script>";
    }





}
