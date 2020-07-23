package com.hulunbuir.clam.afternoon.persistence.controller;

import com.hulunbuir.clam.afternoon.persistence.entity.BuirUserRole;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserRoleService;
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
 * 用户角色关联表 Controller
 * </p>
 *
 * @author wangjunming
 * @since 2020-07-23 12:24:51
 */
@Slf4j
@Api(tags = "用户角色关联表 Controller")
@RestController
@RequestMapping("/buirUserRole")
public class BuirUserRoleController extends BaseController {

    @Autowired
    private IBuirUserRoleService buirUserRoleService;

    @ApiOperation("用户角色关联表分页列表")
    @GetMapping("/buirUserRolePage")
    public JsonResult BuirUserRolePage(QueryRequest queryRequest, BuirUserRole buirUserRole){
        return JsonResult.success(getDataTable(buirUserRoleService.buirUserRolePage(queryRequest,buirUserRole)));
    }

    @ApiOperation("用户角色关联表添加")
    @PostMapping("/saveBuirUserRole")
    public JsonResult saveBuirUserThird(BuirUserRole buirUserRole){
        return JsonResult.success(buirUserRoleService.saveBuirUserRole(buirUserRole));
    }

    @ApiOperation("用户角色关联表修改")
    @PostMapping("/updateBuirUserRole")
    public JsonResult updateBuirUserThird(BuirUserRole buirUserRole){
        return JsonResult.success(buirUserRoleService.updateBuirUserRole(buirUserRole));
    }

    @ApiOperation("用户角色关联表获取")
    @GetMapping("/getOneBuirUserRole")
    public JsonResult getOneBuirUserRole(BuirUserRole buirUserRole){
        return JsonResult.success(buirUserRoleService.getOneBuirUserRole(buirUserRole));
    }

}
