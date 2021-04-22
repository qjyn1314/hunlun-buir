package com.hulunbuir.admin.controller;

import com.hulunbuir.admin.submit.NoRepeatSubmit;
import com.hulunbuir.admin.threadstudy.threadpool.ThreadService;
import com.hulunbuir.admin.worktest.SupplierPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

//    @Reference(check = false)
//    private AuthProvider authProvider;
//
//    @ApiOperation("测试ResuqstBody")
//    @PostMapping("/testContent")
//    public JsonResult testContent(@RequestBody ContentPo contentPo) {
//        log.info("contentPo：{}", contentPo);
//        try {
//            return JsonResult.success(authProvider.queryUser("zhangsan"));
//        } catch (Exception e) {
//            log.error("调用dubbo接口异常，", e);
//            return JsonResult.error();
//        }
//    }

    @Autowired
    private ThreadService service;

    @NoRepeatSubmit
    @ApiOperation("测试线程池")
    @PostMapping("/threadServiceSayHello")
    public void threadServiceSayHello(@RequestParam String username) throws ExecutionException, InterruptedException {
        service.sayHello(username);
        final CompletableFuture<String> completableFuture = service.sayHelloAndReturn(username);
        final String re = completableFuture.get();
        log.info("re:{}", re);
    }



    @ApiOperation("测试http直接调用")
    @PostMapping("/testHttp")
    public void testHttp(@RequestBody List<SupplierPo> supplierPo) {
        log.info("supplierPo：{}", supplierPo);

    }









}
