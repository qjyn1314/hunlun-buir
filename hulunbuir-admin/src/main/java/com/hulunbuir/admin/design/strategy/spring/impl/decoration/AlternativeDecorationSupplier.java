package com.hulunbuir.admin.design.strategy.spring.impl.decoration;

import com.hulunbuir.admin.design.strategy.spring.model.SupplierDto;
import com.hulunbuir.admin.design.strategy.spring.model.SupplierEnum;
import com.hulunbuir.admin.design.strategy.spring.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p>
 * explain: 备选供应商装饰者的具体实现类
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 18:30
 */
@Slf4j
@Service
public class AlternativeDecorationSupplier extends BaseDecorationSupplier {


    @Autowired
    @Qualifier("alternativeSupplier")
    private SupplierService<SupplierDto> supplierService;

    /**
     * 获取供应商类型
     */
    @Override
    public SupplierEnum getType() {
        return SupplierEnum.ALTERNATIVE;
    }

    /**
     * 处理
     */
    @Override
    public Boolean saveSupplier(SupplierDto supplierDto) {
        supplierService.saveSupplier(supplierDto);
        //在此处理需要增加的功能
        return null;
    }


}
