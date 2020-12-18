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
 * 权限表 Entity
 *
 * @author Mr.Wang
 * @date 2020-09-22 11:04:50
 */
@Data
@TableName("sys_permission")
@ApiModel(value="SysPermission对象", description="权限表")
public class SysPermission implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    @TableField("per_name")
    private String perName;

    /**
     * 权限编码
     */
    @ApiModelProperty(value = "权限编码")
    @TableField("per_code")
    private String perCode;

    /**
     * 权限路径
     */
    @ApiModelProperty(value = "权限路径")
    @TableField("per_url")
    private String perUrl;

    /**
     * 图表展示
     */
    @ApiModelProperty(value = "图表展示")
    @TableField("per_icon")
    private String perIcon;

    /**
     * 权限说明
     */
    @ApiModelProperty(value = "权限说明")
    @TableField("description")
    private String description;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("per_sort")
    private Integer perSort;

    /**
     * 是否启用：1-启用；2-不启用
     */
    @ApiModelProperty(value = "是否启用：1-启用；2-不启用")
    @TableField("per_status")
    private Integer perStatus;

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
