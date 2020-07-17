package ${basePackage}.${entityPoPackage};

<#if hasDate = true>
import java.util.Date;
</#if>
<#if hasBigDecimal = true>
import java.math.BigDecimal;
</#if>
<#if hasOffsetDateTime = true>
import java.time.OffsetDateTime;
</#if>

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ${tableComment} Po
 *
 * @author ${author}
 * @since ${date}
 */
public class ${className}InsertPo implements Serializable{

<#if columns??>
    <#list columns as column>
    /**
    * ${column.remark}
    */
    @ApiModelProperty(value = "${column.remark}")
        <#if (column.type = 'varchar' || column.type = 'text' || column.type = 'uniqueidentifier'
        || column.type = 'varchar2' || column.type = 'nvarchar' || column.type = 'VARCHAR2'
        || column.type = 'VARCHAR'|| column.type = 'CLOB' || column.type = 'char')>
    private String ${column.field?uncap_first};

    public String get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(String ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }

        </#if>
        <#if column.type = 'timestamp' || column.type = 'date' || column.type = 'datetime'||column.type = 'TIMESTAMP' || column.type = 'DATE' || column.type = 'DATETIME'>
            <#if column.field = 'CreateDate' || column.field = 'UpdateDate'>
    private OffsetDateTime ${column.field?uncap_first};

    public OffsetDateTime get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(OffsetDateTime ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }
            <#else>
    private Date ${column.field?uncap_first};

    public Date get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(Date ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }

            </#if>
        </#if>
        <#if column.type = 'int' || column.type = 'smallint'>
    private Integer ${column.field?uncap_first};

    public Integer get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(Integer ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }

        </#if>
        <#if column.type = 'bigint'>
    private Long ${column.field?uncap_first};

    public Long get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(Long ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }

        </#if>
        <#if column.type = 'double'>
    private Double ${column.field?uncap_first};

    public Double get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(Double ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }

        </#if>
        <#if column.type = 'tinyint'>
    private Byte ${column.field?uncap_first};

    public Byte get${column.field}() {
        return ${column.field?uncap_first};
    }
    public void set${column.field}(Byte ${column.field?uncap_first}) {
        this.${column.field?uncap_first} = ${column.field?uncap_first};
    }

        </#if>
        <#if column.type = 'decimal' || column.type = 'numeric'>
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
