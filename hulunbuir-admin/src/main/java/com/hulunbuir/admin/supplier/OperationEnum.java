package com.hulunbuir.admin.supplier;

import lombok.Getter;

/**
 * <p>
 * explain: 供应商业务操作类型
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/17 11:43
 */
public enum OperationEnum {

    /**询价*/
    INQUIRY(10,"询价"),
    /**竞价*/
    BIDDING(20,"竞价"),
    /**招投标*/
    THE_BIDDING(30,"招投标"),
    /**订单*/
    ORDER(40,"订单"),
    /**合同*/
    CONTRACT(50,"合同"),

    ;

    private @Getter int code;
    private @Getter String desc;

    OperationEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
