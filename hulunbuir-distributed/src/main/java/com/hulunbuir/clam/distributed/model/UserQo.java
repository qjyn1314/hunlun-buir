package com.hulunbuir.clam.distributed.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-25 16:42
 */
public class UserQo implements Serializable {


    @ApiModelProperty(value = "用户名(登录名)")
    private String userName;


    @ApiModelProperty(value = "用户名(登录名)")
    public String getUserName() {
        return this.userName;
    }

    @ApiModelProperty(value = "用户名(登录名)")
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
