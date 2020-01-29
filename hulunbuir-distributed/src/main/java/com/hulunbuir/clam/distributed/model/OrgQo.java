package com.hulunbuir.clam.distributed.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-18 11:56
 */
public class OrgQo implements Serializable {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父级ID")
    private Integer parentId;


    @ApiModelProperty(value = "名称")
    public String getName() {
        return this.name;
    }

    @ApiModelProperty(value = "名称")
    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "父级ID")
    public Integer getParentId() {
        return this.parentId;
    }

    @ApiModelProperty(value = "父级ID")
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
