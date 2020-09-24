package com.hulunbuir.clam.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/6/17 17:45
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags = "测试控制层")
public class AdminController {

    @ApiOperation("测试ResuqstBody")
    @PostMapping("/testContent")
    public void testContent(@RequestBody ContentPo contentPo){
        log.info("contentPo：{}",contentPo);
    }

}
