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
 * 角色表 Entity
 *
 * @author Mr.Wang
 * @date 2020-09-22 11:04:50
 */
@Data
@TableName("sys_role")
@ApiModel(value="SysRole对象", description="角色表")
public class SysRole implements Serializable {

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
     * 角色说明
     */
    @ApiModelProperty(value = "角色说明")
    @TableField("description")
    private String description;

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

}
