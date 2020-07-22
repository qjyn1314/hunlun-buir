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
 * 权限表 Entity
 *
 * @author wangjunming
 * @date 2020-07-22 14:23:29
 */
@Data
@TableName("buir_permission")
@ApiModel(value="BuirPermission对象", description="权限表")
public class BuirPermission implements Serializable {

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /**
     * 权限说明
     */
    @ApiModelProperty(value = "权限说明")
    @TableField("description")
    private String description;

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
     * 权限编码
     */
    @ApiModelProperty(value = "权限编码")
    @TableField("per_code")
    private String perCode;

    /**
     * 图表展示
     */
    @ApiModelProperty(value = "图表展示")
    @TableField("per_icon")
    private String perIcon;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    @TableField("per_name")
    private String perName;

    /**
     * 权限路径
     */
    @ApiModelProperty(value = "权限路径")
    @TableField("per_url")
    private String perUrl;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("updated_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

}
