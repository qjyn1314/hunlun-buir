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
 * 角色权限关联表
 * </p>
 *
 * @author wangjunming
 * @since 2020-05-25
 */
@TableName("buir_role_permission")
@ApiModel(value="BuirRolePermission对象", description="角色权限关联表")
public class BuirRolePermission extends Model<BuirRolePermission> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(value = "权限ID")
    @TableField("permission_id")
    private Integer permissionId;

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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
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
        return "BuirRolePermission{" +
        "id=" + id +
        ", roleId=" + roleId +
        ", permissionId=" + permissionId +
        ", createdTime=" + createdTime +
        ", updatedTime=" + updatedTime +
        "}";
    }
}
