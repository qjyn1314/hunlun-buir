package com.hulunbuir.clam.afternoon.persistence.controller;

import com.hulunbuir.clam.afternoon.persistence.entity.BuirPermission;
import com.hulunbuir.clam.afternoon.persistence.qo.BuirPermissionTree;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirPermissionService;
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
 * 权限表 Controller
 * </p>
 *
 * @author wangjunming
 * @since 2020-07-22 14:23:29
 */
@Slf4j
@Api(tags = "权限表 Controller")
@RestController
@RequestMapping("/buirPermission")
public class BuirPermissionController extends BaseController {

    @Autowired
    private IBuirPermissionService buirPermissionService;

    @ApiOperation("权限表分页列表")
    @GetMapping("/buirPermissionPage")
    public JsonResult buirPermissionPage(QueryRequest queryRequest, BuirPermission buirPermission){
        return JsonResult.success(getDataTable(buirPermissionService.buirPermissionPage(queryRequest,buirPermission)));
    }

    @ApiOperation("权限表添加")
    @PostMapping("/saveBuirPermission")
    public JsonResult saveBuirPermission(BuirPermission buirPermission){
        return JsonResult.success(buirPermissionService.saveBuirPermission(buirPermission));
    }

    @ApiOperation("权限表修改")
    @PostMapping("/updateBuirPermission")
    public JsonResult updateBuirPermission(BuirPermission buirPermission){
        return JsonResult.success(buirPermissionService.updateBuirPermission(buirPermission));
    }

    @ApiOperation("权限表获取")
    @GetMapping("/getOneBuirPermission")
    public JsonResult getOneBuirPermission(BuirPermission buirPermission){
        return JsonResult.success(buirPermissionService.getOneBuirPermission(buirPermission));
    }

    @ApiOperation("权限表获取")
    @GetMapping("/getPermissionTree")
    public JsonResult getPermissionTree(){
        BuirPermissionTree permissionTree = new BuirPermissionTree(Integer.valueOf("0"));
        return JsonResult.success(buirPermissionService.getPermissionTree(permissionTree));
    }

}
