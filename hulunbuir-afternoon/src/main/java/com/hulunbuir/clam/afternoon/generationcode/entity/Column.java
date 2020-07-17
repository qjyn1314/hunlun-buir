package com.hulunbuir.clam.afternoon.generationcode.entity;

import lombok.Data;

/**
 * 生成表的列信息
 *
 * @author wangjunming
 * @since 2020/7/14 17:28
 */
@Data
public class Column {
    /**
     * 名称
     */
    private String name;
    /**
     * 是否为主键
     */
    private Boolean isKey;
    /**
     * 类型
     */
    private String type;
    /**
     * 注释
     */
    private String remark;
    /**
     * 属性名称
     */
    private String field;

    /**
     * 属性名称-首字母小写
     */
    private String fieldes;

}
