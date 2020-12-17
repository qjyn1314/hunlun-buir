package com.hulunbuir.admin.supplier;

import lombok.Getter;

/**
 * <p>
 * explain: 供应商 类型/分类
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/17 11:32
 */
public enum VendorTypeEnum {

    /**平台供应商*/
    PLATFORM(10,"平台供应商"),
    /**潜在供应商*/
    POTENTIAL(20,"潜在供应商"),
    /**备选供应商*/
    ALTERNATIVE(30,"备选供应商"),
    /**合格供应商*/
    QUALIFIED(40,"合格供应商"),
    /**淘汰供应商*/
    ELIMINATE(50,"淘汰供应商"),
    /**黑名单*/
    BLACKLIST(60,"黑名单"),

    ;

    private @Getter int code;
    private @Getter String desc;

    VendorTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
