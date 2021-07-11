package com.hulunbuir.admin.design.strategy.enhance_spring.controller;

import com.hulunbuir.admin.design.strategy.enhance_spring.client.HandleEnhanceContent;
import com.hulunbuir.admin.design.strategy.enhance_spring.entity.HandleEnhanceDto;
import com.hulunbuir.admin.design.strategy.enhance_spring.entity.HandleEnhanceEnum;
import com.hulunbuir.admin.design.strategy.enhance_spring.service.HandleEnhanceDecorationService;
import com.hulunbuir.admin.design.strategy.enhance_spring.service.HandleEnhanceService;
import com.hulunbuir.parent.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/7/11 14:22
 */
@RestController
@RequestMapping("/handle/enchance")
public class HandleEnchanceController {


    @Autowired
    private HandleEnhanceContent enhanceContent;

    @RequestMapping("/default")
    public JsonResult handleDefault(@RequestBody HandleEnhanceDto enhanceDto, HttpServletRequest request) {
        HandleEnhanceService handleEnhanceType = enhanceContent.getHandleEnhanceType(HandleEnhanceEnum.DEFAULT);
        handleEnhanceType.dealWith(enhanceDto);
        return JsonResult.success();
    }


    @RequestMapping("/default/decoration")
    public JsonResult handleDefaultByDecoration(@RequestBody HandleEnhanceDto enhanceDto, HttpServletRequest request) {
        HandleEnhanceDecorationService handleEnhanceDecoration = enhanceContent.getHandleEnhanceDecorationType(HandleEnhanceEnum.DEFAULT);
        handleEnhanceDecoration.dealWith(enhanceDto);
        return JsonResult.success();
    }


}
