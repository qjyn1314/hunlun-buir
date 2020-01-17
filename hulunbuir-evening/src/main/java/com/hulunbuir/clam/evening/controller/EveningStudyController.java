package com.hulunbuir.clam.evening.controller;

import com.hulunbuir.clam.evening.persistence.entity.Org;
import com.hulunbuir.clam.evening.persistence.service.IOrgService;
import com.hulunbuir.clam.parent.tool.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "Evening项目的学习控制层，仅个人使用")
public class EveningStudyController {

    @Autowired
    private IOrgService orgService;

    /**
     * 获取当前时间
     *
     * @author wangjunming
     * @since 2020/1/16 12:46
     * @return java.lang.String
     */
    @ApiOperation("获取当前时间")
    @GetMapping("/crunDate")
    public String getNowDateTime() {
        log.info("获取当前时间：start");
        String dateTimes = DateUtils.getDateTimes();
        Org byId = orgService.getById(5);
        return byId.toString();
//        log.info("获取当前时间：end");
//        return dateTimes;
    }


}
