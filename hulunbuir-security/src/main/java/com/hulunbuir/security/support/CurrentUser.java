package com.hulunbuir.security.support;

import com.hulunbuir.distributed.evening.AuthUser;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/21 10:08
 */
public class CurrentUser implements Serializable {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Integer id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;
    /**
     * 状态 0锁定 1有效
     */
    @ApiModelProperty(value = "状态 0锁定 1有效")
    private String status;
    /**
     * 最近访问时间
     */
    @ApiModelProperty(value = "最近访问时间")
    private Date lastLoginTime;
    /**
     * 性别 0男 1女 2保密
     */
    @ApiModelProperty(value = "性别 0男 1女 2保密")
    private String sex;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    public CurrentUser() {
    }

    public CurrentUser(AuthUser user) {
        this.id = Math.toIntExact(user.getId());
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.status = user.getStatus();
        this.lastLoginTime = user.getLastLoginTime();
        this.sex = user.getSex();
        this.avatar = user.getAvatar();
        this.description = user.getDescription();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
