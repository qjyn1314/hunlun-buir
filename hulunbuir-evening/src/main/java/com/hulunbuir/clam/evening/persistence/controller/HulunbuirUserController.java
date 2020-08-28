package com.hulunbuir.clam.evening.persistence.controller;

import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.HulunbuirUser;
import com.hulunbuir.clam.evening.persistence.service.IHulunbuirUserService;
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
 * 用户表 Controller
 * </p>
 *
 * @author Mr.Wang
 * @since 2020-08-13 13:40:19
 */
@Slf4j
@Api(tags = "用户表 Controller")
@RestController
@RequestMapping("/h/hulunbuirUser")
public class HulunbuirUserController extends BaseController {

    @Autowired
    private IHulunbuirUserService service;

    @ApiOperation("用户表分页列表")
    @GetMapping("/page")
    public JsonResult page(QueryRequest queryRequest, HulunbuirUser hulunbuirUser){
        return JsonResult.success(getDataTable(service.page(queryRequest,hulunbuirUser)));
    }

    @ApiOperation("用户表添加")
    @PostMapping("/save")
    public JsonResult save(HulunbuirUser hulunbuirUser){
        return JsonResult.success(service.save(hulunbuirUser));
    }

    @ApiOperation("用户表修改")
    @PostMapping("/update")
    public JsonResult update(HulunbuirUser hulunbuirUser){
        return JsonResult.success(service.update(hulunbuirUser));
    }

    @ApiOperation("用户表获取")
    @GetMapping("/selOne")
    public JsonResult selOne(HulunbuirUser hulunbuirUser){
        return JsonResult.success(service.selOne(hulunbuirUser));
    }

}
