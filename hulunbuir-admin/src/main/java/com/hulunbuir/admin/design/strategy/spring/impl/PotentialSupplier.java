package com.hulunbuir.admin.design.strategy.spring.impl;

import com.hulunbuir.admin.design.strategy.spring.model.SupplierDto;
import com.hulunbuir.admin.design.strategy.spring.model.SupplierEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * explain: 潜在供应商具体实现类
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 18:30
 */
@Slf4j
@Service
public class PotentialSupplier extends BaseSupplier {

    /**
     * 获取供应商类型
     */
    @Override
    public SupplierEnum getType() {
        return SupplierEnum.POTENTIAL;
    }

    @Override
    public Boolean saveSupplier(SupplierDto supplierDto) {

        return null;
    }
}
