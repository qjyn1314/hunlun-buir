package com.hulunbuir.admin.design.factory_strategy.factory;

import lombok.Getter;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/18 14:07
 */
public enum FoodTypeEnum {

    /**中国食物*/
    CHINA("CHINA","中国食物"),
    /**美国食物*/
    UNITED("UNITED","美国食物"),
    ;

    private @Getter String code;
    private @Getter String desc;

    FoodTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
