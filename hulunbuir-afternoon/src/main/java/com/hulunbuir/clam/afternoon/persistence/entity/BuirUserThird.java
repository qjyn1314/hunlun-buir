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
 * 第三方登录与用户关联表
 * </p>
 *
 * @author wangjunming
 * @since 2020-06-12
 */
@TableName("buir_user_third")
@ApiModel(value="BuirUserThird对象", description="第三方登录与用户关联表")
public class BuirUserThird extends Model<BuirUserThird> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "第三方关联的唯一标识")
    @TableField("thrid_associated")
    private String thridAssociated;

    @ApiModelProperty(value = "第三方登录类型")
    @TableField("thrid_type")
    private String thridType;

    @ApiModelProperty(value = "第三方登录类型名称")
    @TableField("thrid_type_name")
    private String thridTypeName;

    @ApiModelProperty(value = "第三方登录名称")
    @TableField("thrid_name")
    private String thridName;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


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

    public String getThridAssociated() {
        return thridAssociated;
    }

    public void setThridAssociated(String thridAssociated) {
        this.thridAssociated = thridAssociated;
    }

    public String getThridType() {
        return thridType;
    }

    public void setThridType(String thridType) {
        this.thridType = thridType;
    }

    public String getThridTypeName() {
        return thridTypeName;
    }

    public void setThridTypeName(String thridTypeName) {
        this.thridTypeName = thridTypeName;
    }

    public String getThridName() {
        return thridName;
    }

    public void setThridName(String thridName) {
        this.thridName = thridName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BuirUserThird{" +
        "id=" + id +
        ", userId=" + userId +
        ", thridAssociated=" + thridAssociated +
        ", thridType=" + thridType +
        ", thridTypeName=" + thridTypeName +
        ", thridName=" + thridName +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
