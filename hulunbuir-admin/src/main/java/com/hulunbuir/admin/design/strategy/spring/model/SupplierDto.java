package com.hulunbuir.admin.design.strategy.spring.model;

import lombok.Data;

/**
 * <p>
 * explain: 保存供应商的前端传参
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/26 20:40
 */
@Data
public class SupplierDto {

    private SupplierEnum supplierEnum;

    private String supplierDesc;

    public SupplierEnum getSupplierEnum() {
        return supplierEnum;
    }

    public void setSupplierEnum(SupplierEnum supplierEnum) {
        this.supplierEnum = supplierEnum;
    }

    public String getSupplierDesc() {
        return supplierDesc;
    }

    public void setSupplierDesc(String supplierDesc) {
        this.supplierDesc = supplierDesc;
    }
}
