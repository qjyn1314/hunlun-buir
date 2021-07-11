package com.hulunbuir.admin.design.strategy.enhance_spring.client;

import com.hulunbuir.admin.design.strategy.enhance_spring.entity.HandleEnhanceEnum;
import com.hulunbuir.admin.design.strategy.enhance_spring.service.HandleEnhanceDecorationService;
import com.hulunbuir.admin.design.strategy.enhance_spring.service.HandleEnhanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/7/11 13:32
 */
@Component
public class HandleEnhanceContent {

    @Autowired
    private List<HandleEnhanceService> handleEnhanceServices;
    @Autowired
    private List<HandleEnhanceDecorationService> supplierEnhanceDecorationServices;

    /**
     * 获取基础的处理类
     *
     * @param handleEnhanceEnum 处理类型
     * @author wangjunming
     * @since 2021/7/11 14:20
     * @return com.hulunbuir.admin.design.strategy.enhance_spring.service.HandleEnhanceService
     */
    public HandleEnhanceService getHandleEnhanceType(HandleEnhanceEnum handleEnhanceEnum){
        for (HandleEnhanceService handleEnhanceService : handleEnhanceServices) {
            HandleEnhanceEnum handleEnhance = handleEnhanceService.handleType();
            if(handleEnhance.equals(handleEnhanceEnum)){
                return handleEnhanceService;
            }
        }
        throw new NullPointerException("未找到基础处理类。。。");
    }

    /**
     * 获取增强处理类
     *
     * @param handleEnhanceEnum 处理类型
     * @author wangjunming
     * @since 2021/7/11 14:21
     * @return com.hulunbuir.admin.design.strategy.enhance_spring.service.HandleEnhanceDecorationService
     */
    public HandleEnhanceDecorationService getHandleEnhanceDecorationType(HandleEnhanceEnum handleEnhanceEnum){
        for (HandleEnhanceDecorationService handleEnhanceService : supplierEnhanceDecorationServices) {
            HandleEnhanceEnum handleEnhance = handleEnhanceService.handleType();
            if(handleEnhance.equals(handleEnhanceEnum)){
                return handleEnhanceService;
            }
        }
        throw new NullPointerException("未找到增强基础处理类。。。");
    }



}
