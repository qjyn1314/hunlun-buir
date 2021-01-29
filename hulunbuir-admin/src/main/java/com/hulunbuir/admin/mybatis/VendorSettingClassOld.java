package com.hulunbuir.admin.mybatis;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 供应商分类设置(VendorSettingClass)实体类
 *
 * @author wangjunming
 * @since 2020-12-21 11:38:58
 */
@Data
@Alias("setClass")
public class VendorSettingClassOld implements Serializable {
    private static final long serialVersionUID = 251449848213193942L;
    /**
    * 主键ID
    */
    private Long id;
    /**
    * 父类ID-若是顶级则为0
    */
    private Long pid;
    /**
     * 分类名称
     */
    private String name;

    /**
    * 供应商类型：10-平台供应商，20-潜在供应商，30-备选供应商，40-合格供应商，50-淘汰供应商，60-黑名单
    */
    private Integer vendorType;

    /**
     * 层级
     */
    private Integer hierarchy;

    private VendorSettingClassOld children;

}