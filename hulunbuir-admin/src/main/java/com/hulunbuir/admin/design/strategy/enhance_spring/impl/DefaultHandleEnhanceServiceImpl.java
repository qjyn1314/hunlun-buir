package com.hulunbuir.admin.design.strategy.enhance_spring.impl;

import com.hulunbuir.admin.design.strategy.enhance_spring.entity.HandleEnhanceDto;
import com.hulunbuir.admin.design.strategy.enhance_spring.entity.HandleEnhanceEnum;
import com.hulunbuir.admin.design.strategy.enhance_spring.service.HandleEnhanceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/7/11 14:09
 */
@Service
public class DefaultHandleEnhanceServiceImpl implements HandleEnhanceService {


    @Override
    public HandleEnhanceEnum handleType() {
        return HandleEnhanceEnum.DEFAULT;
    }

    @Override
    public void dealWith(HandleEnhanceDto enhanceDto) {

    }


}
