package com.hulunbuir.evening.controller;

import com.hulunbuir.parent.result.JsonResult;
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


    @PostMapping
    public JsonResult ceshi(){

//        final String request = RestTemplateService.restRequest();

        return JsonResult.success();
    }

}
