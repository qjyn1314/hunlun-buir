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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 用户表信息
 * </p>
 *
 * @author wangjunming
 * @since 2020-02-12
 */
@TableName("user")
@ApiModel(value="User对象", description="用户表信息")
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名称")
    @TableField("user_name")
    @NotBlank(message = "请输入用户名称")
    @Size(max = 10,min = 3,message = "请输入长度在3~10之间的字符串")
    private String userName;

    @ApiModelProperty(value = "用户邮箱")
    @TableField("user_mail")
    @NotBlank(message = "请输入用户邮箱")
    @Email(message = "请输入正确的邮箱地址")
    private String userMail;

    @ApiModelProperty(value = "用户密码")
    @TableField("user_password")
    @NotBlank(message = "请输入用户密码")
    @Size(min = 1,max = 16,message = "请输入1~16位字符作为您的密码")
    private String userPassword;

    @ApiModelProperty(value = "密码盐值")
    @TableField("password_salt")
    private String passwordSalt;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
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
        return "User{" +
        "id=" + id +
        ", userName=" + userName +
        ", userMail=" + userMail +
        ", userPassword=" + userPassword +
        ", passwordSalt=" + passwordSalt +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
