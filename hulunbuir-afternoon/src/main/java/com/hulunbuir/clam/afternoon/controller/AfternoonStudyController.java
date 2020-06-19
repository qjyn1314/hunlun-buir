package com.hulunbuir.clam.afternoon.controller;

import com.hulunbuir.clam.common.base.BaseController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Explain:学习springboot+dubbo+mybatis-plus+redis搭建工程
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 12:44
 */
@Slf4j
@RestController
@RequestMapping("/study")
@Api(tags = "Afternoon项目的学习控制层，仅个人使用")
public class AfternoonStudyController extends BaseController {

//    @DubboReference(check = false,timeout = 500000,retries = 0)
//    private EveningProvider eveningProvider;


//    @Autowired
//    private IBuirUserService buirUserService;
//
//    public  vaildateBuirUser(){
//
//    }


}
