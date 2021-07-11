package com.hulunbuir.admin.design.strategy.enhance_spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/7/11 14:00
 */
public enum HandleEnhanceEnum {

    /**默认处理的类型*/
    DEFAULT(1,"默认处理类"),

    ;

    HandleEnhanceEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    private Integer key;
    private String value;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
