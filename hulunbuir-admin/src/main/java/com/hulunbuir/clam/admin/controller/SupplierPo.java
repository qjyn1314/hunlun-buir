package com.hulunbuir.clam.admin.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/1 20:17
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class SupplierPo {

    private String phone;
    private String supplierCode;
    private String username;

}
