package com.hulunbuir.clam.afternoon.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author wangjunming
 * @since 2020-05-25
 */
@TableName("buir_user_role")
@ApiModel(value="BuirUserRole对象", description="用户角色关联表")
public class BuirUserRole extends Model<BuirUserRole> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Integer roleId;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
        return "BuirUserRole{" +
        "id=" + id +
        ", userId=" + userId +
        ", roleId=" + roleId +
        ", createdTime=" + createdTime +
        ", updatedTime=" + updatedTime +
        "}";
    }
}
