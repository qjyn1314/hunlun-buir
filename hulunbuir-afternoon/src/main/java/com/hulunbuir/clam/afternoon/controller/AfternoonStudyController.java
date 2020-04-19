package com.hulunbuir.clam.afternoon.controller;

import com.alibaba.fastjson.JSON;
import com.hulunbuir.clam.afternoon.persistence.entity.KoUser;
import com.hulunbuir.clam.afternoon.persistence.service.IKoUserService;
import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.distributed.evening.EveningProvider;
import com.hulunbuir.clam.parent.tool.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Reference(check = false,timeout = 500000,retries = 0)
    private EveningProvider eveningProvider;

    @Autowired
    private IKoUserService userService;

    /**
     * 获取当前时间
     *
     * @author wangjunming
     * @since 2020/1/16 12:46
     * @return java.lang.String
     */
    @ApiOperation("获取当前时间")
    @GetMapping("/nowDate")
    @ResponseBody
    public Object getNowDateTime() {
        KoUser user = new KoUser();
        user.setUserName("排骨-"+DateUtils.getDateTimes());
//        boolean flag = userService.insertUserGlob(user);
        List<KoUser> list = userService.list();
        return JSON.toJSON(list);
    }


}
