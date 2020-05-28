package com.hulunbuir.clam.afternoon.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.hulunbuir.clam.afternoon.params.qo.RegUser;
import com.hulunbuir.clam.route.config.shiro.ShiroTool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表信息
 * </p>
 *
 * @author wangjunming
 * @since 2020-05-25
 */
@TableName("buir_user")
@ApiModel(value="BuirUser对象", description="用户信息表信息")
public class BuirUser extends Model<BuirUser> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户昵称")
    @TableField("nick_name")
    @NotBlank(message = "请输入用户名称")
    @Size(max = 10,min = 3,message = "请输入长度在3~10之间的字符串")
    private String nickName;

    @ApiModelProperty(value = "用户登录邮箱")
    @TableField("user_name")
    @NotBlank(message = "请输入用户邮箱")
    @Email(message = "请输入正确的邮箱地址")
    private String userName;

    @ApiModelProperty(value = "用户登录密码")
    @TableField("password")
    @NotBlank(message = "请输入用户密码")
    @Size(min = 1,max = 16,message = "请输入1~16位字符作为您的密码")
    private String password;

    @ApiModelProperty(value = "密码盐值")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "是否冻结；0-待审核；1-审核通过；2-冻结；")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    public BuirUser() {
    }

    public BuirUser(RegUser regUser) {
        String randomSalt = ShiroTool.getRandomSalt(15);
        String userPassword = regUser.getUserPassword();
        this.nickName = regUser.getUserName();
        this.userName = regUser.getUserMail();
        this.password = ShiroTool.encryptPassword(userPassword, randomSalt);
        this.salt = randomSalt;
        this.status = UserStatus.TO_AUDIT;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    private BuirUser(@NotBlank(message = "请输入用户邮箱") @Email(message = "请输入正确的邮箱地址") String userName) {
        this.userName = userName;
    }

    public static BuirUser buildByMail(String userName){
        return new BuirUser(userName);
    }

    public String getNickName() {
        return nickName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BuirUser{" +
        "id=" + id +
        ", nickName=" + nickName +
        ", userName=" + userName +
        ", password=" + password +
        ", salt=" + salt +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
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

    public static class UserStatus {
        //待审核
        public static final Integer TO_AUDIT = 0;
        //已审核
        public static final Integer THE_APPROVED = 1;
        //已冻结
        public static final Integer FREEZE = 2;
    }
}
