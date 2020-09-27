package com.hulunbuir.clam.evening.persistence.controller;

import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.SysPermission;
import com.hulunbuir.clam.evening.persistence.service.ISysPermissionService;
import com.hulunbuir.clam.evening.persistence.vo.LayPermissionTree;
import com.hulunbuir.clam.evening.persistence.vo.SysPermissionTree;
import com.hulunbuir.clam.parent.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 权限表 Controller
 * </p>
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
@Slf4j
@Api(tags = "权限表 Controller")
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController extends BaseController {

    @Autowired
    private ISysPermissionService service;

    @ApiOperation("权限表分页列表")
    @GetMapping("/page")
    public Map<String, Object> page(QueryRequest queryRequest, SysPermission sysPermission) {
        return getLayTable(service.page(queryRequest, sysPermission));
    }

    @ApiOperation("权限表添加")
    @PostMapping("/save")
    public JsonResult save(SysPermission sysPermission) {
        return JsonResult.success(service.save(sysPermission));
    }

    @ApiOperation("权限表修改")
    @PostMapping("/update")
    public JsonResult update(SysPermission sysPermission) {
        return JsonResult.success(service.update(sysPermission));
    }

    @ApiOperation("权限表获取")
    @GetMapping("/selOne")
    public JsonResult selOne(SysPermission sysPermission) {
        return JsonResult.success(service.selOne(sysPermission));
    }

    @ApiOperation("树形权限列表")
    @GetMapping("/permissionTree")
    public JsonResult permissionTree() {
        return JsonResult.success(service.permissionTree(new SysPermissionTree(Integer.valueOf("0"))));
    }

    @ApiOperation("树形权限列表")
    @GetMapping("/layPermissionTree")
    public JsonResult layPermissionTree() {
        return JsonResult.success(service.layPermissionTree(new LayPermissionTree(Integer.valueOf("0"))));
    }

}
