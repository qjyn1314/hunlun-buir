package ${basePackage}.${entityPoPackage};

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ${tableComment} IdPo
 *
 * @author ${author}
 * @since ${date}
 */
public class ${className}IdPo implements Serializable{

    <#if columns??>
        <#list columns as column>
         <#if column.name = 'id'>
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
         </#if>
        </#list>
    </#if>
}
