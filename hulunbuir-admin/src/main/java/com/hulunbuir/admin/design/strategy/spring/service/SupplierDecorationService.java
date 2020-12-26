package com.hulunbuir.admin.design.strategy.spring.service;

import com.hulunbuir.admin.design.strategy.spring.model.SupplierEnum;

/**
 * <p>
 * explain: 装饰的供应商策略接口
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 18:28
 */
public interface SupplierDecorationService<T> extends SupplierService<T> {

    String ALTERNATIVE_DECORATION = "alternativeDecorationSupplier";

    /**
     * 获取供应商类型
     */
    @Override
    SupplierEnum getType();

    /**
     * 此为公共的保存供应商的逻辑
     */
    @Override
    Boolean saveSupplier(T t);


}
