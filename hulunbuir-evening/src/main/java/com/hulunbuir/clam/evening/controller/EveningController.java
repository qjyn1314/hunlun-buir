package com.hulunbuir.clam.evening.controller;

import com.hulunbuir.clam.common.config.RestTemplateService;
import com.hulunbuir.clam.parent.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/1 20:35
 */
@RestController
public class EveningController {

    @Autowired
    private RestTemplateService restTemplate;

    @PostMapping
    public JsonResult ceshi(){

//        final String request = RestTemplateService.restRequest();

        return JsonResult.success();
    }

}
