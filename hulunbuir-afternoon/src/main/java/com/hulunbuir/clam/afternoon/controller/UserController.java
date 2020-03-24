package com.hulunbuir.clam.afternoon.controller;


import com.hulunbuir.clam.afternoon.persistence.entity.User;
import com.hulunbuir.clam.afternoon.persistence.service.IUserService;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import com.hulunbuir.clam.parent.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户表信息 前端控制器
 * </p>
 *
 * @author wangjunming
 * @since 2020-02-12
 */
@Slf4j
@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 注册用户
     *
     * @param user:
     * @return com.hulunbuir.clam.parent.result.JsonResult
     * @author wangjunming
     * @since 2020/2/12 17:36
     */
    @ApiOperation("注册用户")
    @PostMapping("/regUser")
    public JsonResult regUser(@Valid User user){
        boolean regUserFlag = false;
        try {
            regUserFlag = userService.regUser(user);
        } catch (HulunBuirException e) {
            log.error("业务异常!!",e);
            return JsonResult.error(e.getMessage());
        } catch (Exception e){
            log.error("系统异常!!",e);
            return JsonResult.error("系统异常!");
        }
        return JsonResult.success(regUserFlag);
    }

}

