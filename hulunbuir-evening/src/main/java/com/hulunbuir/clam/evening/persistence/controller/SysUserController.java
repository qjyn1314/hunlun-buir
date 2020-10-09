package com.hulunbuir.clam.evening.persistence.controller;

import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.SysUser;
import com.hulunbuir.clam.evening.persistence.service.ISysUserService;
import com.hulunbuir.clam.evening.persistence.vo.SysUserVo;
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
 * 用户表 Controller
 * </p>
 *
 * @author Mr.Wang
 * @since 2020-09-18 10:33:50
 */
@Slf4j
@Api(tags = "用户表 Controller")
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService service;

    @ApiOperation("用户表分页列表")
    @GetMapping("/page")
    public Map<String, Object> page(QueryRequest queryRequest, SysUser sysUser) {
        return getLayTable(service.page(queryRequest, sysUser));
    }

    @ApiOperation("用户表添加")
    @PostMapping("/save")
    public JsonResult save(SysUser sysUser) {
        return JsonResult.success(service.save(sysUser));
    }

    @ApiOperation("用户表修改")
    @PostMapping("/update")
    public JsonResult update(SysUser sysUser) {
        return JsonResult.success(service.update(sysUser));
    }

    @ApiOperation("用户表获取")
    @GetMapping("/selOne")
    public JsonResult selOne(SysUser sysUser) {
        return JsonResult.success(service.selOne(sysUser));
    }

    @ApiOperation("用户表获取")
    @GetMapping("/queryOne")
    public JsonResult queryOne(SysUserVo sysUser) {
        return JsonResult.success(service.queryOne(sysUser));
    }

    @ApiOperation("用户表删除")
    @PostMapping("/del")
    public JsonResult del(SysUser sysUser) {
        return JsonResult.success(service.del(sysUser.getId()));
    }

}
