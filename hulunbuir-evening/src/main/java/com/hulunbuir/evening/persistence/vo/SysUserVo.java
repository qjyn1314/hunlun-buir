package com.hulunbuir.evening.persistence.vo;

import com.hulunbuir.evening.persistence.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户表 Entity
 *
 * @author Mr.Wang
 * @date 2020-09-18 10:33:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserVo extends SysUser implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long roleId;

    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 角色说明
     */
    @ApiModelProperty(value = "角色说明")
    private String description;






}
