package com.hulunbuir.clam.afternoon.controller;


import com.alibaba.fastjson.JSON;
import com.hulunbuir.clam.afternoon.persistence.entity.User;
import com.hulunbuir.clam.afternoon.persistence.service.IUserService;
import com.hulunbuir.clam.parent.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表信息 前端控制器
 * </p>
 *
 * @author wangjunming
 * @since 2020-02-12
 */
@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 注册用户
     *
     * @author wangjunming
     * @since 2020/2/12 17:36
     * @param user:
     * @return com.hulunbuir.clam.parent.result.JsonResult
     */
    @ApiOperation("注册用户")
    @PostMapping("/regUser")
    public JsonResult regUser(@Valid User user) {
        boolean regUserFlag = userService.regUser(user);
        return JsonResult.success(regUserFlag);
    }

}

