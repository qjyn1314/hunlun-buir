package com.hulunbuir.clam.evening.persistence.controller;

import com.hulunbuir.common.base.QueryRequest;
import com.hulunbuir.clam.evening.controller.BaseController;
import com.hulunbuir.clam.evening.persistence.entity.SysRolePermission;
import com.hulunbuir.clam.evening.persistence.service.ISysRolePermissionService;
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
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
@Slf4j
@Api(tags = "角色权限关联表 Controller")
@RestController
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController extends BaseController {

    @Autowired
    private ISysRolePermissionService service;

    @ApiOperation("角色权限关联表分页列表")
    @GetMapping("/page")
    public JsonResult page(QueryRequest queryRequest, SysRolePermission sysRolePermission) {
        return JsonResult.success(getDataTable(service.page(queryRequest, sysRolePermission)));
    }

    @ApiOperation("角色权限关联表添加")
    @PostMapping("/save")
    public JsonResult save(SysRolePermission sysRolePermission) {
        return JsonResult.success(service.save(sysRolePermission));
    }

    @ApiOperation("角色权限关联表修改")
    @PostMapping("/update")
    public JsonResult update(SysRolePermission sysRolePermission) {
        return JsonResult.success(service.update(sysRolePermission));
    }

    @ApiOperation("角色权限关联表获取")
    @GetMapping("/selOne")
    public JsonResult selOne(SysRolePermission sysRolePermission) {
        return JsonResult.success(service.selOne(sysRolePermission));
    }

}
