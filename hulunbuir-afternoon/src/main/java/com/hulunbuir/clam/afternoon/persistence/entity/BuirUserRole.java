package com.hulunbuir.clam.afternoon.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色关联表 Entity
 *
 * @author wangjunming
 * @date 2020-07-23 12:24:51
 */
@Data
@TableName("buir_user_role")
@ApiModel(value="BuirUserRole对象", description="用户角色关联表")
public class BuirUserRole implements Serializable {

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private Date createdTime;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Integer roleId;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("updated_time")
    private Date updatedTime;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    public BuirUserRole(BuirUser buirUser) {
        this.userId = buirUser.getId();
        this.roleId = buirUser.getRoleId();
        final Date nowDate = new Date();
        this.createdTime = nowDate;
        this.updatedTime = nowDate;
    }


    public BuirUserRole(Integer userId) {
        this.userId = userId;
    }

    public BuirUserRole() {
    }

    public BuirUserRole(Date createdTime, Integer id, Integer roleId, Date updatedTime, Integer userId) {
        this.createdTime = createdTime;
        this.id = id;
        this.roleId = roleId;
        this.updatedTime = updatedTime;
        this.userId = userId;
    }
}
