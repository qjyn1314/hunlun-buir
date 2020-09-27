package com.hulunbuir.clam.evening.persistence.vo;

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
public class SysPermissionTree implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private Integer pid;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String title;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 跳转的URL
     */
    @ApiModelProperty(value = "跳转的URL")
    private String href = "";

    /**
     * 是否展开
     */
    @ApiModelProperty(value = "是否展开")
    private boolean spread = true;

    /**
     * 子节点
     */
    @ApiModelProperty(value = "子节点")
    private List<SysPermissionTree> children;

    public SysPermissionTree(Integer pid) {
        this.pid = pid;
    }
}
