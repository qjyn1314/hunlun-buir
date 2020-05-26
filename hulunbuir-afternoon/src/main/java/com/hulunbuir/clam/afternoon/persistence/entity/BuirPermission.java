package com.hulunbuir.clam.afternoon.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author wangjunming
 * @since 2020-05-25
 */
@TableName("buir_permission")
@ApiModel(value="BuirPermission对象", description="权限表")
public class BuirPermission extends Model<BuirPermission> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限名称")
    @TableField("permission")
    private String permission;

    @ApiModelProperty(value = "权限路径")
    @TableField("permission_url")
    private String permissionUrl;

    @ApiModelProperty(value = "权限说明")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BuirPermission{" +
        "id=" + id +
        ", permission=" + permission +
        ", permissionUrl=" + permissionUrl +
        ", description=" + description +
        ", createdTime=" + createdTime +
        ", updatedTime=" + updatedTime +
        "}";
    }
}
