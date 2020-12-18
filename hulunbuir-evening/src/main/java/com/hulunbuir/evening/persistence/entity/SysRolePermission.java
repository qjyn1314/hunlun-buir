package com.hulunbuir.evening.persistence.entity;

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
 * 角色权限关联表 Entity
 *
 * @author Mr.Wang
 * @date 2020-09-22 11:04:50
 */
@Data
@TableName("sys_role_permission")
@ApiModel(value="SysRolePermission对象", description="角色权限关联表")
public class SysRolePermission implements Serializable {

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
     * 权限ID
     */
    @ApiModelProperty(value = "权限ID")
    @TableField("permission_id")
    private Integer permissionId;

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

    public SysRolePermission(Integer roleId, Integer permissionId) {
        final Date nowDate = new Date();
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.createdTime = nowDate;
        this.updatedTime = nowDate;
    }
}
