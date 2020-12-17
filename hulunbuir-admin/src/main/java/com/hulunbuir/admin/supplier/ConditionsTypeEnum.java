package com.hulunbuir.admin.supplier;

import lombok.Getter;

/**
 * <p>
 * explain: 供应商分类变更条件类型
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/17 11:43
 */
public enum ConditionsTypeEnum {

    /**上传附件*/
    UPLOAD_ATTACHMENTS(10,"上传附件"),
    /**提交评估表*/
    SUBMIT_ASSESSMENT(20,"提交评估表"),

    ;

    private @Getter int code;
    private @Getter String desc;

    ConditionsTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
