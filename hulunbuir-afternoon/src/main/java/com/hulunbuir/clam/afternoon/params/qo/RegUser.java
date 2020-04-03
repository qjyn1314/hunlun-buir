package com.hulunbuir.clam.afternoon.params.qo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * Explain:用户登录传参
 * </p >
 *
 * @author wangjunming
 * @since 2020-03-03 12:45
 */
public class RegUser implements Serializable {

    @ApiModelProperty(value = "用户名称")
    @TableField("user_name")
    @NotBlank(message = "请输入用户名称")
    @Size(max = 10,min = 3,message = "请输入长度在3~10之间的字符串作为用户名")
    private String userName;

    @ApiModelProperty(value = "用户邮箱")
    @NotBlank(message = "请输入用户邮箱")
    @Email(message = "请输入正确的邮箱地址")
    private String userMail;

    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "请输入用户密码")
    @Size(min = 1, max = 16, message = "请输入1~16位字符作为您的密码")
    private String userPassword;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "请输入验证码")
    @Size(max = 12, min = 6, message = "请输入长度在6~12之间的字符串")
    private String verification;


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

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}
