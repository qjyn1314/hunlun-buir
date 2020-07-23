package com.hulunbuir.clam.afternoon.persistence.controller;


import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserService;
import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.distributed.model.UserManager;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import com.hulunbuir.clam.parent.result.JsonResult;
import com.hulunbuir.clam.route.config.jwt.CurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息表信息 前端控制器
 * </p>
 *
 * @author wangjunming
 * @since 2020-05-25
 */
@Api(tags = "用户控制层")
@Slf4j
@Validated
@RestController
@RequestMapping("/buirUser")
public class BuirUserController extends BaseController {

    @Autowired
    private IBuirUserService buirUserService;

    @ApiOperation("用户分页列表")
    @GetMapping("/userPage")
    public JsonResult userPage(QueryRequest queryRequest, BuirUser buirUser) {
        final UserManager userMessage = CurrentUser.getUserMessage();
        log.info("当前登录用户信息是：{}", userMessage);
        return JsonResult.success(getDataTable(buirUserService.userPage(queryRequest, buirUser)));
    }

    @ApiOperation("添加用户")
    @PostMapping("/userAdd")
    public JsonResult userAdd(BuirUser buirUser) {
        final UserManager userMessage = CurrentUser.getUserMessage();
        log.info("当前登录用户信息是：{}", userMessage);
        boolean flag = false;
        try {
            buirUser = new BuirUser(buirUser);
            buirUserService.validate(buirUser, 1);
            flag = buirUserService.regUser(buirUser);
            log.info("添加用户是否成功！！：{}",flag);
            return JsonResult.success(flag);
        } catch (HulunBuirException e) {
            log.error("业务异常!!", e);
            return JsonResult.error(e.getMessage());
        } catch (Exception e) {
            log.error("系统异常!!", e);
            return JsonResult.error("系统异常!");
        }
    }

    @ApiOperation("编辑用户")
    @PostMapping("/userEdit")
    public JsonResult userEdit(BuirUser buirUser) {
        final UserManager userMessage = CurrentUser.getUserMessage();
        log.info("当前登录用户信息是：{}", userMessage);
        boolean flag = false;
        try {
            buirUserService.validate(buirUser, 2);
            flag = buirUserService.userEdit(buirUser);
            return JsonResult.success(flag);
        } catch (HulunBuirException e) {
            log.error("业务异常!!", e);
            return JsonResult.error(e.getMessage());
        } catch (Exception e) {
            log.error("系统异常!!", e);
            return JsonResult.error("系统异常!");
        }
    }

    @ApiOperation("删除用户")
    @PostMapping("/userDel")
    public JsonResult userDel(BuirUser buirUser) {
        final UserManager userMessage = CurrentUser.getUserMessage();
        log.info("当前登录用户信息是：{}", userMessage);
        boolean flag = false;
        try {
            buirUserService.validate(buirUser, 3);
            flag = buirUserService.userDel(buirUser);
            return JsonResult.success(flag);
        } catch (HulunBuirException e) {
            log.error("业务异常!!", e);
            return JsonResult.error(e.getMessage());
        } catch (Exception e) {
            log.error("系统异常!!", e);
            return JsonResult.error("系统异常!");
        }
    }

}

