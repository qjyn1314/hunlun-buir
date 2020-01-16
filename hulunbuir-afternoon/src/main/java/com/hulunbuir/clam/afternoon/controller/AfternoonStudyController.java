package com.hulunbuir.clam.afternoon.controller;

import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
public class AfternoonStudyController extends BaseController {

    /**
     * 获取当前时间
     * 
     * @author wangjunming
     * @since 2020/1/16 12:46
     * @return java.lang.String
     */
    @GetMapping("/nowDate")
    public String getNowDateTime() {
        log.info("获取当前时间：start");
        String dateTimes = DateUtils.getDateTimes();
        log.info("获取当前时间：end");
        return dateTimes;
    }


}
