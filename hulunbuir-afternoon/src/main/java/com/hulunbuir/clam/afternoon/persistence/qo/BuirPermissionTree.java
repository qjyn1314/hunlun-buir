package com.hulunbuir.clam.afternoon.persistence.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 权限表 Tree
 *
 * @author wangjunming
 * @date 2020-07-22 14:23:29
 */
@Data
public class BuirPermissionTree implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private Integer parentId;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String title;

    /**
     * 是否展开
     */
    @ApiModelProperty(value = "是否展开")
    private boolean spread = true;

    /**
     * 子节点
     */
    @ApiModelProperty(value = "子节点")
    private List<BuirPermissionTree> children;

    public BuirPermissionTree(Integer parentId) {
        this.parentId = parentId;
    }
}
