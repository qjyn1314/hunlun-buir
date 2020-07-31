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
 * 角色权限关联表 Entity
 *
 * @author wangjunming
 * @date 2020-07-23 17:58:42
 */
@Data
@TableName("buir_role_permission")
@ApiModel(value="BuirRolePermission对象", description="角色权限关联表")
public class BuirRolePermission implements Serializable {

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
     * 权限ID
     */
    @ApiModelProperty(value = "权限ID")
    @TableField("permission_id")
    private Integer permissionId;

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

    public BuirRolePermission(BuirRole buirRole, String permissionId) {
        final Date date = new Date();
        this.roleId = buirRole.getId();
        this.permissionId = Integer.valueOf(permissionId);
        this.createdTime = date;
        this.updatedTime = date;
    }

}
