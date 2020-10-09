package com.hulunbuir.clam.evening.persistence.entity;

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
 * @author Mr.Wang
 * @date 2020-09-22 11:04:50
 */
@Data
@TableName("sys_user_role")
@ApiModel(value="SysUserRole对象", description="用户角色关联表")
public class SysUserRole implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Integer roleId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("updated_time")
    private Date updatedTime;

    public SysUserRole() {
    }

    public SysUserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }


}
