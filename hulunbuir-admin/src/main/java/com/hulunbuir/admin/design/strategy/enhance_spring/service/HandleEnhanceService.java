package com.hulunbuir.admin.design.strategy.enhance_spring.service;

import com.hulunbuir.admin.design.strategy.enhance_spring.entity.HandleEnhanceDto;
import com.hulunbuir.admin.design.strategy.enhance_spring.entity.HandleEnhanceEnum;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/7/11 13:42
 */
public interface HandleEnhanceService {


    HandleEnhanceEnum handleType();


    void dealWith(HandleEnhanceDto enhanceDto);


}
