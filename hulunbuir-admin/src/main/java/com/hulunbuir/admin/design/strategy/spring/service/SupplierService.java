package com.hulunbuir.admin.design.strategy.spring.service;

import com.hulunbuir.admin.design.strategy.spring.model.SupplierEnum;

/**
 * <p>
 * explain: 被装饰的供应商策略接口
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 18:28
 */
public interface SupplierService<T> {

    String PLATFORM = "platformSupplier";
    String POTENTIAL = "potentialSupplier";
    String ALTERNATIVE = "alternativeSupplier";

    /**
     * 获取供应商类型
     */
    SupplierEnum getType();

    /**
     * 此为公共的保存供应商的逻辑
     */
    Boolean saveSupplier(T t);


}
