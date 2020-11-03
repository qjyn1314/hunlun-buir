package com.hulunbuir.clam.evening.generationcode.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据源表 Entity
 *
 * @author Mr.Wang
 * @date 2020-10-20 14:32:13
 */
@Data
@TableName("datasource_conf")
@ApiModel(value = "DatasourceConf对象", description = "数据源表")
public class DatasourceConf implements Serializable {

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_date")
    private Date createDate;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记")
    @TableField("del_flag")
    private String delFlag;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * SPRING中动态数据源的名称
     */
    @ApiModelProperty(value = "SPRING中动态数据源的名称")
    @TableField("datasource_name")
    private String datasourceName;

    /**
     * 名称
     */
    @ApiModelProperty(value = "数据库名称")
    @TableField("database_name")
    private String databaseName;

    /**
     * 数据源IP
     */
    @ApiModelProperty(value = "数据库IP")
    @TableField("database_ip")
    private String databaseIp;

    /**
     * 数据源端口号
     */
    @ApiModelProperty(value = "数据库端口号")
    @TableField("database_port")
    private String databasePort;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    /**
     * 更新
     */
    @ApiModelProperty(value = "更新")
    @TableField("update_date")
    private Date updateDate;

    /**
     * jdbc_url
     */
    @ApiModelProperty(value = "jdbc_url")
    @TableField("url")
    private String url;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;


    public String formatUrl(){
        return String.format(this.url,this.databaseIp,this.databasePort);
    }

}
