package com.hulunbuir.clam.admin.controller;

import com.hulunbuir.clam.distributed.afternoon.ManagerUserProvider;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/6/17 17:45
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Reference
    private ManagerUserProvider userProvider;


//    public String queryUser(){
//        Map<String,Object>
//        return userProvider.queryBuirUser();
//    }







}
