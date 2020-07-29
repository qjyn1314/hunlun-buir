package com.hulunbuir.clam.afternoon.persistence.controller;

import com.hulunbuir.clam.afternoon.persistence.entity.BuirRolePermission;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirRolePermissionService;
import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.parent.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色权限关联表 Controller
 * </p>
 *
 * @author wangjunming
 * @since 2020-07-23 17:58:42
 */
@Slf4j
@Api(tags = "角色权限关联表 Controller")
@RestController
@RequestMapping("/buirRolePermission")
public class BuirRolePermissionController extends BaseController {

    @Autowired
    private IBuirRolePermissionService buirRolePermissionService;

    @ApiOperation("角色权限关联表分页列表")
    @GetMapping("/buirRolePermissionPage")
    public JsonResult buirRolePermissionPage(QueryRequest queryRequest, BuirRolePermission buirRolePermission){
        return JsonResult.success(getDataTable(buirRolePermissionService.buirRolePermissionPage(queryRequest,buirRolePermission)));
    }

    @ApiOperation("角色权限关联表添加")
    @PostMapping("/saveBuirRolePermission")
    public JsonResult saveBuirRolePermission(BuirRolePermission buirRolePermission){
        return JsonResult.success(buirRolePermissionService.saveBuirRolePermission(buirRolePermission));
    }

    @ApiOperation("角色权限关联表修改")
    @PostMapping("/updateBuirRolePermission")
    public JsonResult updateBuirRolePermission(BuirRolePermission buirRolePermission){
        return JsonResult.success(buirRolePermissionService.updateBuirRolePermission(buirRolePermission));
    }

    @ApiOperation("角色权限关联表获取")
    @GetMapping("/getOneBuirRolePermission")
    public JsonResult getOneBuirRolePermission(BuirRolePermission buirRolePermission){
        return JsonResult.success(buirRolePermissionService.getOneBuirRolePermission(buirRolePermission));
    }

}
