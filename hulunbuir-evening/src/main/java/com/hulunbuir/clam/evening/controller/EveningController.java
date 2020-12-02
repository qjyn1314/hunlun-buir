package com.hulunbuir.clam.evening.controller;

import com.hulunbuir.clam.parent.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @PostMapping
    public JsonResult ceshi(){

//        final String request = RestTemplateService.restRequest();

        return JsonResult.success();
    }

}
