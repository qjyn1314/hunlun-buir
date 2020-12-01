package com.hulunbuir.clam.admin.controller;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/1 20:17
 */
@Data
public class SupplierPo {

    List<UsersPo> users;

    private String supplierCode;

    private String supplierName;

}
