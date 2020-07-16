package com.hulunbuir.clam.afternoon.controller;


import com.hulunbuir.clam.afternoon.persistence.entity.BuirUserThird;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserThirdService;
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
 * 第三方登录与用户关联表 前端控制器
 * </p>
 *
 * @author wangjunming
 * @since 2020-06-12
 */
@Slf4j
@Api(tags = "第三方登录与用户关联表 前端控制器")
@RestController
@RequestMapping("/buirUserThird")
public class BuirUserThirdController extends BaseController {

    @Autowired
    private IBuirUserThirdService buirUserThirdService;


    @ApiOperation("用户分页列表")
    @GetMapping("/buirUserThirdPage")
    public JsonResult buirUserThirdPage(QueryRequest queryRequest, BuirUserThird buirUserThird){
        return JsonResult.success(getDataTable(buirUserThirdService.buirUserThirdPage(queryRequest,buirUserThird)));
    }

    @ApiOperation("用户添加")
    @PostMapping("/saveBuirUserThird")
    public JsonResult saveBuirUserThird(BuirUserThird buirUserThird){
        return JsonResult.success(buirUserThirdService.saveBuirUserThird(buirUserThird));
    }

    @ApiOperation("用户修改")
    @PostMapping("/updateBuirUserThird")
    public JsonResult updateBuirUserThird(BuirUserThird buirUserThird){
        return JsonResult.success(buirUserThirdService.updateBuirUserThird(buirUserThird));
    }

    @ApiOperation("用户获取")
    @GetMapping("/getOneBuirUserThird")
    public JsonResult getOneBuirUserThird(BuirUserThird buirUserThird){
        return JsonResult.success(buirUserThirdService.getOneBuirUserThird(buirUserThird));
    }





}

