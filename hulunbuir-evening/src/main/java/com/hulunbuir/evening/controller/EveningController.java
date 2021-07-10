package com.hulunbuir.evening.controller;

import com.hulunbuir.distributed.admin.AdminMailProvider;
import com.hulunbuir.parent.result.JsonResult;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/1 20:35
 */
@RestController
public class EveningController {


    /**进行发送邮件的服务*/
    @DubboReference
    private AdminMailProvider mailProvider;

    @PostMapping("/ceshi")
    public JsonResult ceshi(){
        mailProvider.sendSimpleMail("qjyn1314@163.com","测试dubbo3.0.0","非常荣幸的邀请您来参加dubbo3.0.0的测试。","");
        return JsonResult.success();
    }

}
