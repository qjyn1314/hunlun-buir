package com.hulunbuir.clam.evening.controller;

import com.hulunbuir.clam.common.base.Pages;
import com.hulunbuir.clam.distributed.model.OrgQo;
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
     * @return java.lang.String
     * @author wangjunming
     * @since 2020/1/16 12:46
     */
    @ApiOperation("获取当前时间")
    @GetMapping("/crunDate")
    public String getNowDateTime() {
        log.info("获取当前时间：start");
        String dateTimes = DateUtils.getDateTimes();
        Org byId = orgService.getById(5);
        OrgQo orgQo = new OrgQo();
        orgService.insertOrg(orgQo);
        return byId.toString();
    }


    /**
     * 分页列表示例：
     *
     * @param page: {
     *              current：当前页
     *              size:每页显示总记录数
     *              }
     * @return com.hulunbuir.clam.common.base.Pages<com.hulunbuir.clam.evening.persistence.entity.Org>
     * @author wangjunming
     * @since 2020/1/18 11:44
     */
    @ApiOperation("获取分页列表")
    @GetMapping("/orgPage")
    public Pages<Org> selectOrgPage(Pages<Org> page) {
        return orgService.selectOrgPage(page);
    }


}
