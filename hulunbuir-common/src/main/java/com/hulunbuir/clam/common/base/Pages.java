package com.hulunbuir.clam.common.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * Explain:工程中使用到的分页，进行继承mybatis-plus的分页类，并增加封装参数
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-18 11:39
 */
public class Pages<T> extends Page<T> {

    @ApiModelProperty(value = "分页参数中的其他参数，比如：当前页面中的汇总信息")
    private Object otherDate;

    public Object getOtherDate() {
        return this.otherDate;
    }

    public void setOtherDate(Object otherDate) {
        this.otherDate = otherDate;
    }

}
