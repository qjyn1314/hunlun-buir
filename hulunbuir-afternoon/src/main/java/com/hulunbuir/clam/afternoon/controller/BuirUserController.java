package com.hulunbuir.clam.afternoon.controller;


import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserService;
import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.parent.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表信息 前端控制器
 * </p>
 *
 * @author wangjunming
 * @since 2020-05-25
 */
@Api(tags = "用户控制层")
@RestController
@RequestMapping("/buirUser")
public class BuirUserController extends BaseController {


    @Autowired
    private IBuirUserService buirUserService;

    @ApiOperation("用户分页列表")
    @GetMapping("/userPage")
    public JsonResult userPage(QueryRequest queryRequest,BuirUser buirUser){
        return JsonResult.success(getDataTable(buirUserService.userPage(queryRequest,buirUser)));
    }

}

