package com.hulunbuir.evening.persistence.vo;

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
     * 权限编码
     */
    @ApiModelProperty(value = "权限编码")
    private String percode;

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

    public SysPermissionTree() {
    }

    public SysPermissionTree(Integer pid) {
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<SysPermissionTree> getChildren() {
        return children;
    }

    public void setChildren(List<SysPermissionTree> children) {
        this.children = children;
    }
}
