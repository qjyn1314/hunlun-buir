package com.hulunbuir.clam.afternoon.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hulunbuir.clam.common.mail.MailConstants;
import com.hulunbuir.clam.distributed.admin.AdminMailProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
        String zhuce = "仼少";
        String content = MailConstants.ZHU_CE.getContent();
        String formatcontent = String.format(content, zhuce);
        mailService.sendSimpleMail("qjyn1314@foxmail.com", MailConstants.ZHU_CE.getSubject(), formatcontent);
        return "index";
    }


}
