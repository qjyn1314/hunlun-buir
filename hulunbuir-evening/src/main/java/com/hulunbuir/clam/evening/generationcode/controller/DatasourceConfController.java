package com.hulunbuir.clam.evening.generationcode.controller;

import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.controller.BaseController;
import com.hulunbuir.clam.evening.generationcode.entity.DatasourceConf;
import com.hulunbuir.clam.evening.generationcode.service.IDatasourceConfService;
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
 * 数据源表 Controller
 * </p>
 *
 * @author Mr.Wang
 * @since 2020-10-20 14:32:13
 */
@Slf4j
@Api(tags = "数据源表 Controller")
@RestController
@RequestMapping("/datasourceConf")
public class DatasourceConfController extends BaseController {

    @Autowired
    private IDatasourceConfService service;

    @ApiOperation("数据源表分页列表")
    @GetMapping("/page")
    public Map<String, Object> page(QueryRequest queryRequest, DatasourceConf datasourceConf){
        return getLayTable(service.page(queryRequest,datasourceConf));
    }

    @ApiOperation("数据源表添加")
    @PostMapping("/save")
    public JsonResult save(DatasourceConf datasourceConf){
        return JsonResult.success(service.save(datasourceConf));
    }

    @ApiOperation("数据源表修改")
    @PostMapping("/update")
    public JsonResult update(DatasourceConf datasourceConf){
        return JsonResult.success(service.update(datasourceConf));
    }

    @ApiOperation("数据源表获取")
    @GetMapping("/selOne")
    public JsonResult selOne(DatasourceConf datasourceConf){
        return JsonResult.success(service.selOne(datasourceConf));
    }

}
