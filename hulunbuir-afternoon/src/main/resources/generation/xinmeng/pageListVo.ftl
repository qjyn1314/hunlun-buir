package ${basePackage}.${entityVoPackage};

<#if hasBigDecimal = true>
import java.math.BigDecimal;
</#if>
<#if hasOffsetDateTime = true>
import java.time.OffsetDateTime;
</#if>

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
* ${className}分页查询PageListVo-${tableComment} ：${className}PageListVo
*/
@ApiModel("${className}分页查询PageListVo")
public class ${className}PageListVo implements Serializable{

<#if columns??>
    <#list columns as column>
        <#if (column.type = 'varchar' || column.type = 'text' || column.type = 'uniqueidentifier'
        || column.type = 'varchar2' || column.type = 'nvarchar' || column.type = 'VARCHAR2'
        || column.type = 'VARCHAR'|| column.type = 'CLOB' || column.type = 'char')>

            <#if column.name = 'create_by' || column.name = 'update_by' || column.name = 'remarks' || column.name = 'del_flag'>
            <#else>
    /**
    * ${column.remark}
    */
    @ApiModelProperty(value = "${column.remark}")
    private String ${column.field?uncap_first};

    public String get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(String ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }
            </#if>
        </#if>
        <#if column.type = 'timestamp' || column.type = 'date' || column.type = 'datetime'||column.type = 'TIMESTAMP' || column.type = 'DATE' || column.type = 'DATETIME'>
            <#if column.name = 'create_date' || column.name = 'update_date'>
            <#else>
    /**
    * ${column.remark}
    */
    @ApiModelProperty(value = "${column.remark}")
    private OffsetDateTime ${column.field?uncap_first};

    public OffsetDateTime get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(OffsetDateTime ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }
            </#if>
        </#if>
        <#if column.type = 'int' || column.type = 'smallint'>
    /**
    * ${column.remark}
    */
    @ApiModelProperty(value = "${column.remark}")
    private Integer ${column.field?uncap_first};

    public Integer get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(Integer ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }
        </#if>
        <#if column.type = 'bigint'>
            <#if column.name = 'id' || column.name = 'id'>
            <#else>
    /**
    * ${column.remark}
    */
    @ApiModelProperty(value = "${column.remark}")
    private Long ${column.field?uncap_first};

    public Long get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(Long ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }
            </#if>
        </#if>
        <#if column.type = 'double'>
    /**
    * ${column.remark}
    */
    @ApiModelProperty(value = "${column.remark}")
    private Double ${column.field?uncap_first};

    public Double get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(Double ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }
        </#if>
        <#if column.type = 'tinyint'>
    /**
    * ${column.remark}
    */
    @ApiModelProperty(value = "${column.remark}")
    private Byte ${column.field?uncap_first};

    public Byte get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(Byte ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }
        </#if>
        <#if column.type = 'decimal' || column.type = 'numeric'>
    /**
    * ${column.remark}
    */
    @ApiModelProperty(value = "${column.remark}")
    private BigDecimal ${column.field?uncap_first};

    public BigDecimal get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(BigDecimal ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }
        </#if>
    </#list>
</#if>
}
