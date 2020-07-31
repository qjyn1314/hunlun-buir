package com.hulunbuir.clam.afternoon.vo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * explain:登录用户的权限
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/30 17:32
 */
public class PermissionVo implements Serializable {

    List<PermissionVo> children;
    private Integer id;
    private Integer parentId;
    private String perName;
    private String url;
    private String perIcon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerIcon() {
        return perIcon;
    }

    public void setPerIcon(String perIcon) {
        this.perIcon = perIcon;
    }

    public List<PermissionVo> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionVo> children) {
        this.children = children;
    }

}
