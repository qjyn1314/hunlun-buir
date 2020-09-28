package com.hulunbuir.clam.evening.persistence.vo;

import com.hulunbuir.clam.evening.persistence.entity.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表 Entity
 *
 * @author Mr.Wang
 * @date 2020-09-22 11:04:50
 */
@Data
@ApiModel(value="SysRole对象", description="角色表")
public class SysRoleVo extends SysRole implements Serializable {

    /**
     * 角色相应的权限ID
     */
    @ApiModelProperty(value = "角色相应的权限ID")
    private String permission;

    /**
     * 角色相应的权限名称
     */
    @ApiModelProperty(value = "角色相应的权限名称")
    private String permissionName;

}
