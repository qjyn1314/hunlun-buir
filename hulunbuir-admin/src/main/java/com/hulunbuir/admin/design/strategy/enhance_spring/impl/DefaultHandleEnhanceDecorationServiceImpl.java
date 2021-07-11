package com.hulunbuir.admin.design.strategy.enhance_spring.impl;

import com.hulunbuir.admin.design.strategy.enhance_spring.entity.HandleEnhanceDto;
import com.hulunbuir.admin.design.strategy.enhance_spring.entity.HandleEnhanceEnum;
import com.hulunbuir.admin.design.strategy.enhance_spring.service.HandleEnhanceDecorationService;
import com.hulunbuir.admin.design.strategy.enhance_spring.service.HandleEnhanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * explain: 对处理的service进行增强
 * </p>
 *
 * @author wangjunming
 * @since 2021/7/11 13:42
 */
@Service
public class DefaultHandleEnhanceDecorationServiceImpl  implements HandleEnhanceDecorationService {


    @Override
    public HandleEnhanceEnum handleType() {
        return HandleEnhanceEnum.DEFAULT;
    }

    @Override
    public void dealWith(HandleEnhanceDto enhanceDto) {

    }

}
