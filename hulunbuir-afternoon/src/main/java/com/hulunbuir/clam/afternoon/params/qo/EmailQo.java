package com.hulunbuir.clam.afternoon.params.qo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/3/27 12:33
 */
public class EmailQo implements Serializable {

    @ApiModelProperty(value = "用户邮箱")
    @NotBlank(message = "请输入用户邮箱")
    @Email(message = "请输入正确的邮箱地址")
    private String userMail;

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
}
