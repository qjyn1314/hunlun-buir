package com.hulunbuir.admin.design.strategy.spring.model;

import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * explain:  供应商类型枚举类，需要根据此枚举类返回不同的供应商具体实现类
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 18:28
 */
@ToString
public enum SupplierEnum {

    /**
     * 平台供应商
     */
    PLATFORM(10, "平台供应商"),
    /**
     * 潜在供应商
     */
    POTENTIAL(20, "潜在供应商"),
    /**
     * 备选供应商
     */
    ALTERNATIVE(30, "备选供应商"),
    /**
     * 合格供应商
     */
    QUALIFIED(40, "合格供应商"),
    /**
     * 淘汰供应商
     */
    ELIMINATE(50, "淘汰供应商"),
    /**
     * 黑名单
     */
    BLACKLIST(60, "黑名单"),
    ;

    final private @Getter
    int code;
    final private @Getter
    String desc;

    SupplierEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
