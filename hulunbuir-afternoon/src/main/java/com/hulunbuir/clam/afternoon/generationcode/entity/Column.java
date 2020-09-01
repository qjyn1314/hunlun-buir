package com.hulunbuir.clam.afternoon.generationcode.entity;

import com.hulunbuir.clam.parent.tool.CommonUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 生成表的列信息
 *
 * @author wangjunming
 * @since 2020/7/14 17:28
 */
@Data
public class Column implements Serializable {
    /**
     * 列名称
     */
    private String name;
    /**
     * 是否为主键
     */
    private Boolean isKey;
    /**
     * 类型
     */
    private String type;
    /**
     * 类型-对应的数据库大写类型-->#{id,jdbcType=BIGINT}
     */
    private String typeCapital;
    /**
     * 列注释
     */
    private String remark;
    /**
     * 属性名称
     */
    private String field;
    /**
     * 属性名称-首字母小写
     */
    private String fields;

    //#{id,jdbcType=BIGINT}
    private static String capitalType(String type) {
        switch (type) {
            case "BIGINT":
            case "bigint":
                return "BIGINT";
            case "BIT":
            case "bit":
                return "BIT";
            case "BLOB":
            case "blob":
                return "BLOB";
            case "CHAR":
            case "char":
                return "CHAR";
            case "TEXT":
            case "text":
                return "TEXT";
            case "DATE":
            case "date":
                return "DATE";
            case "DECIMAL":
            case "decimal":
                return "DECIMAL";
            case "DOUBLE":
            case "double":
                return "DOUBLE";
            case "FLOAT":
            case "float":
                return "FLOAT";
            case "INTEGER":
            case "int":
            case "integer":
                return "INTEGER";
            case "NUMERIC":
            case "numeric":
                return "NUMERIC";
            case "REAL":
            case "real":
                return "REAL";
            case "SMALLINT":
            case "smallint":
                return "SMALLINT";
            case "TIME":
            case "time":
                return "TIME";
            case "TIMESTAMP":
            case "timestamp":
            case "DATETIME":
            case "datetime":
                return "TIMESTAMP";
            case "TINYINT":
            case "tinyint":
                return "TINYINT";
            case "VARCHAR":
            case "varchar":
                return "VARCHAR";
        }
        return null;
    }

    public String getField() {
        this.field = CommonUtils.underscoreToCamel(StringUtils.lowerCase(this.getName()));
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFields() {
        this.fields = CommonUtils.lowerFirstLatter(CommonUtils.underscoreToCamel(StringUtils.lowerCase(this.getName())));
        return fields;
    }

    public void setFields(String fields) {
       this.fields = fields;
    }

    public void setTypeCapital(String typeCapital) {
        this.typeCapital = typeCapital;
    }

    public String getTypeCapital() {
        this.typeCapital = capitalType(this.getType());
        return typeCapital;
    }

}
