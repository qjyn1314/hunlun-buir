package com.hulunbuir.clam.afternoon.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表 Entity
 *
 * @author wangjunming
 * @date 2020-07-23 10:09:33
 */
@Data
@TableName("buir_role")
@ApiModel(value="BuirRole对象", description="角色表")
public class BuirRole implements Serializable {

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /**
     * 角色说明
     */
    @ApiModelProperty(value = "角色说明")
    @TableField("description")
    private String description;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    @TableField("role_code")
    private String roleCode;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("updated_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    /**
     * 权限逗号分隔字符串
     */
    @ApiModelProperty(value = "权限逗号分隔字符串")
    @TableField(exist = false)
    private String permission;

    /**
     * 权限名称逗号分隔字符串
     */
    @ApiModelProperty(value = "权限名称逗号分隔字符串")
    @TableField(exist = false)
    private String permissionName;



}
