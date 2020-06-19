package com.hulunbuir.clam.evening.controller;


import com.hulunbuir.clam.distributed.admin.AdminMailProvider;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wangjunming
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/hulunbuirUser")
public class HulunbuirUserController {

    @Reference
    private AdminMailProvider mailProvider;

}

