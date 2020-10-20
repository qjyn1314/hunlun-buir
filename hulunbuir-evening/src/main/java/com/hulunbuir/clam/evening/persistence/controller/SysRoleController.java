package com.hulunbuir.clam.evening.persistence.controller;

import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.controller.BaseController;
import com.hulunbuir.clam.evening.persistence.entity.SysRole;
import com.hulunbuir.clam.evening.persistence.service.ISysRoleService;
import com.hulunbuir.clam.evening.persistence.vo.SysRoleVo;
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
 * 角色表 Controller
 * </p>
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
@Slf4j
@Api(tags = "角色表 Controller")
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService service;

    @ApiOperation("角色表分页列表")
    @GetMapping("/page")
    public Map<String, Object> page(QueryRequest queryRequest, SysRole sysRole) {
        return getLayTable(service.page(queryRequest, sysRole));
    }

    @ApiOperation("角色表添加")
    @PostMapping("/save")
    public JsonResult save(SysRoleVo sysRole) {
        return JsonResult.success(service.save(sysRole));
    }

    @ApiOperation("角色表修改")
    @PostMapping("/update")
    public JsonResult update(SysRoleVo sysRole) {
        return JsonResult.success(service.update(sysRole));
    }

    @ApiOperation("角色表获取")
    @GetMapping("/selOne")
    public JsonResult selOne(SysRole sysRole) {
        return JsonResult.success(service.selOne(sysRole));
    }

    @ApiOperation("角色表获取")
    @GetMapping("/list")
    public JsonResult list(SysRole sysRole) {
        return JsonResult.success(service.list(sysRole));
    }

}
