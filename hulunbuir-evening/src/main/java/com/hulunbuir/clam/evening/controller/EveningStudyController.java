package com.hulunbuir.clam.evening.controller;

import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
public class EveningStudyController {

    /**
     * 获取当前时间
     *
     * @author wangjunming
     * @since 2020/1/16 12:46
     * @return java.lang.String
     */
    @GetMapping("/crunDate")
    public String getNowDateTime() {
        log.info("获取当前时间：start");
        String dateTimes = DateUtils.getDateTimes();
        log.info("获取当前时间：end");
        return dateTimes;
    }


}
