package com.hulunbuir.clam.afternoon.persistence.controller;

import com.hulunbuir.clam.afternoon.persistence.entity.BuirRole;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirRoleService;
import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
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
 * 角色表 Controller
 * </p>
 *
 * @author wangjunming
 * @since 2020-07-23 10:09:33
 */
@Slf4j
@Api(tags = "角色表 Controller")
@RestController
@RequestMapping("/buirRole")
public class BuirRoleController extends BaseController {

    @Autowired
    private IBuirRoleService buirRoleService;

    @ApiOperation("角色表分页列表")
    @GetMapping("/buirRolePage")
    public JsonResult buirRolePage(QueryRequest queryRequest, BuirRole buirRole){
        return JsonResult.success(getDataTable(buirRoleService.buirRolePage(queryRequest,buirRole)));
    }

    @ApiOperation("角色表添加")
    @PostMapping("/saveBuirRole")
    public JsonResult saveBuirRole(BuirRole buirRole){
        return JsonResult.success(buirRoleService.saveBuirRole(buirRole));
    }

    @ApiOperation("角色表修改")
    @PostMapping("/updateBuirRole")
    public JsonResult updateBuirRole(BuirRole buirRole){
        final boolean updateBuirRole;
        try {
            updateBuirRole = buirRoleService.updateBuirRole(buirRole);
        } catch (HulunBuirException e) {
            return JsonResult.error(e.getMessage());
        } catch (Exception e){
            log.error("系统错误！",e);
            return JsonResult.error("系统错误!");
        }
        return JsonResult.success(updateBuirRole);
    }

    @ApiOperation("角色表获取")
    @GetMapping("/getOneBuirRole")
    public JsonResult getOneBuirRole(BuirRole buirRole){
        return JsonResult.success(buirRoleService.getOneBuirRole(buirRole));
    }

    @ApiOperation("角色表列表获取")
    @GetMapping("/findInfoList")
    public JsonResult findInfoList(BuirRole buirRole){
        return JsonResult.success(buirRoleService.findInfoList(buirRole));
    }

}
