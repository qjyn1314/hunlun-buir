package com.hulunbuir.clam.afternoon.generationcode.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * explain: 需要生成代码的表
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/4 16:18
 */
public class CodeGeneration implements Serializable {

    /**
     * 数据库名称
     */
    private String schemaName;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 数据量（行）
     */
    private Long dataRows;
    /**
     * 备注
     */
    private String remark;
    /**
     * 编码方式
     */
    private String coding;

    public CodeGeneration() {
    }

    public CodeGeneration(String schemaName) {
        this.schemaName = schemaName;
    }

    public CodeGeneration(String schemaName, String tableName) {
        this.schemaName = schemaName;
        this.tableName = tableName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getDataRows() {
        return dataRows;
    }

    public void setDataRows(Long dataRows) {
        this.dataRows = dataRows;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }
}
