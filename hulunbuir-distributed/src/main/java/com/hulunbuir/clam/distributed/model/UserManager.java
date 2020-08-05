package com.hulunbuir.clam.distributed.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * explain:登录用户使用
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/24 11:57
 */
@Data
public class UserManager implements Serializable {

    private Integer userId;

    private String nickName;

    private String userName;

    @ApiModelProperty(value = "是否冻结；0-待审核；1-审核通过；2-冻结；")
    private Integer status;

    private Date createTime;

    private Long roleId;

    private String roleName;

    private String roleCode;

    private String description;

    private String password;

    private String salt;

    public void setNull() {
        this.password = null;
        this.salt = null;
    }

}
