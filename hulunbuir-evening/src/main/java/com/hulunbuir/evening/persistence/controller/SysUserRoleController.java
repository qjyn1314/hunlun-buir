package com.hulunbuir.evening.persistence.controller;

import com.hulunbuir.common.base.QueryRequest;
import com.hulunbuir.evening.controller.BaseController;
import com.hulunbuir.evening.persistence.entity.SysUserRole;
import com.hulunbuir.evening.persistence.service.ISysUserRoleService;
import com.hulunbuir.parent.result.JsonResult;
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
 * 用户角色关联表 Controller
 * </p>
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
@Slf4j
@Api(tags = "用户角色关联表 Controller")
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController extends BaseController {

    @Autowired
    private ISysUserRoleService service;

    @ApiOperation("用户角色关联表分页列表")
    @GetMapping("/page")
    public JsonResult page(QueryRequest queryRequest, SysUserRole sysUserRole) {
        return JsonResult.success(getDataTable(service.page(queryRequest, sysUserRole)));
    }

    @ApiOperation("用户角色关联表添加")
    @PostMapping("/save")
    public JsonResult save(SysUserRole sysUserRole) {
        return JsonResult.success(service.save(sysUserRole));
    }

    @ApiOperation("用户角色关联表修改")
    @PostMapping("/update")
    public JsonResult update(SysUserRole sysUserRole) {
        return JsonResult.success(service.update(sysUserRole));
    }

    @ApiOperation("用户角色关联表获取")
    @GetMapping("/selOne")
    public JsonResult selOne(SysUserRole sysUserRole) {
        return JsonResult.success(service.selOne(sysUserRole));
    }

}
