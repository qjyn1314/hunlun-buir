package com.hulunbuir.admin.supplier;

import lombok.Getter;

/**
 * <p>
 * explain: 是否必填、是否参与操作、是否启用、是否直接转化、是否需要审批
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/17 11:43
 */
public enum EnableEnum {

    /**是*/
    ENABLE(10,"是"),
    /**否*/
    DISABLE(20,"否"),

    ;

    private @Getter int code;
    private @Getter String desc;

    EnableEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
