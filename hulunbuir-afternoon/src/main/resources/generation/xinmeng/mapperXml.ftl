<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.${mapperPackage}.${className}Dao">

    <#--全表字段-基础查询-->
    <sql id="Base_colum_list">
        ${columnsCommaSeparated}
    </sql>

    <sql id="query_where">
        <where>
            <#if columns??>
                <#list columns as column>
                     <#if (column.type = 'varchar' || column.type = 'text' || column.type = 'uniqueidentifier'
                            || column.type = 'varchar2' || column.type = 'nvarchar' || column.type = 'VARCHAR2'
                            || column.type = 'VARCHAR'|| column.type = 'CLOB' || column.type = 'char')>
            <if test="${className?uncap_first}.${column.fieldes} != null and ${className?uncap_first}.${column.fieldes} != ''">
                and  ${column.name} = ${wellNo}${className?uncap_first}.${column.fieldes}}
            </if>
                     <#else>
            <if test="${className?uncap_first}.${column.fieldes} != null ">
                and  ${column.name} = ${wellNo}${className?uncap_first}.${column.fieldes}}
            </if>
                    </#if>
                </#list>
            </#if>
        </where>
    </sql>

    <select id="findPage" resultType="${basePackage}.${entityPackage}.${className}">
        select
        <include refid="Base_colum_list"/>
        from ${tableName}
        <include refid="query_where"/>
        <if test="${className?uncap_first}.pageInfo != null">
            <if test="${className?uncap_first}.pageInfo.pageNo != null and ${className?uncap_first}.page.pageNo != '' and ${className?uncap_first}.pageInfo.pageSize != null and ${className?uncap_first}.page.pageSize != ''">
                limit ${wellNo}${className?uncap_first}.pageInfo.pageNo} , ${wellNo}${className?uncap_first}.pageInfo.pageSize}
            </if>
        </if>
    </select>

    <insert id="insert" >
        INSERT INTO ${tableName} ( <include refid="Base_colum_list"/> ) VALUES ( ${insertSetValue} )
    </insert>

    <update id="update" >
        UPDATE ${tableName} SET ${updateSetValue}  WHERE id = ${wellNo}id}
    </update>

    <select id="getOne${className}" resultType="${basePackage}.${entityVoPackage}.${className}Vo">
        select
        <include refid="Base_colum_list"/>
        from ${tableName}
        <include refid="query_where"/>
    </select>

    <select id="selectByPrimaryKey" resultType="${basePackage}.${entityVoPackage}.${className}Vo">
        select
        <include refid="Base_colum_list"/>
        from ${tableName}
        where id = ${wellNo}id}
    </select>



</mapper>
