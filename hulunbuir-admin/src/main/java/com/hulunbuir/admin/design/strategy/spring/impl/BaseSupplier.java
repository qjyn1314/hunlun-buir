package com.hulunbuir.admin.design.strategy.spring.impl;

import com.hulunbuir.admin.design.strategy.spring.model.SupplierDto;
import com.hulunbuir.admin.design.strategy.spring.service.SupplierService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain: 被装饰的供应商抽象类，为子类提供公共的具体实现
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 18:37
 */
@Slf4j
public abstract class BaseSupplier implements SupplierService<SupplierDto> {


}
