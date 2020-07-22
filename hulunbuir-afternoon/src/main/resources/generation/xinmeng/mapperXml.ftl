<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.${mapperPackage}.${className}Mapper">

    <#--结果映射-->
    <#--<resultMap id="BaseResultMap" type="${basePackage}.${entityPackage}.${className}" >
        <#if columns??>
            <#list columns as column>
                <#if column.name = 'id' || column.name = 'id'>
        <id column="${column.name}" property="${column.fields}" jdbcType="${column.typeCapital}" />
                <#else>
        <result column="${column.name}" property="${column.fields}" jdbcType="${column.typeCapital}" />
                </#if>
            </#list>
        </#if>
    </resultMap>-->

    <#--全表字段-基础查询-->
    <sql id="Base_Column_List">
        ${columnsCommaSeparated}
    </sql>

    <select id="selectByPrimaryKey" resultType="${basePackage}.${entityPackage}.${className}" parameterType="java.lang.Long" >
        select
        <include refid="Base_Column_List" />
        from ${tableName}
        where id = ${idStr}
    </select>

    <delete id="deleteByPrimaryKey" parameterType="java.lang.Long" >
        delete from ${tableName}
        where id = ${idStr}
    </delete>

    <insert id="insert" parameterType="${basePackage}.${entityPackage}.${className}" >
        insert into ${tableName} (
        ${columnsCommaSeparated}
        ) values (
        ${insertSetValue}
        )
    </insert>

    <insert id="insertSelective" parameterType="${basePackage}.${entityPackage}.${className}" >
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
            <#if columns??>
                <#list columns as column>
            <if test="${column.fields} != null" >
                ${column.name},
            </if>
                </#list>
            </#if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
            <#if columns??>
                <#list columns as column>
            <if test="${column.fields} != null" >
                ${wellNo}${column.fields},jdbcType=${column.typeCapital}},
            </if>
                </#list>
            </#if>
        </trim>
    </insert>

    <update id="updateByPrimaryKeySelective" parameterType="${basePackage}.${entityPackage}.${className}" >
        update ${tableName}
        <set >
            <#if columns??>
                <#list columns as column>
            <if test="${column.fields} != null" >
                ${column.name} = ${wellNo}${column.fields},jdbcType=${column.typeCapital}},
            </if>
                </#list>
            </#if>
        </set>
        where id = ${idStr}
    </update>

    <update id="updateByPrimaryKey" parameterType="${basePackage}.${entityPackage}.${className}" >
        update ${tableName}  set
        ${updateByPrimaryKey}
        where id = ${idStr}
    </update>

    <select id="findPageList" resultType="${basePackage}.${entityVoPackage}.${className}PageListVo" parameterType="${basePackage}.${entityPoPackage}.${className}PageListPo" >
        select
        ${infos}
        from
        ${tableName}
        where
        del_flag = '0'
    </select>

    <select id="findInfo" resultType="${basePackage}.${entityVoPackage}.${className}InfoVo" parameterType="java.lang.Long" >
        select
        ${infos}
        from
        ${tableName}
        where
        del_flag = '0'
        and id = ${wellNo}id}
    </select>

    <select id="findInfoes" resultType="${basePackage}.${entityVoPackage}.${className}InfoVo">
        select
        ${infos}
        from ${tableName}
        <include refid="query_where"/>
    </select>

    <select id="findInfoList" resultType="${basePackage}.${entityVoPackage}.${className}InfoVo">
        select
        ${infos}
        from ${tableName}
        <include refid="query_where"/>
    </select>


    <select id="findExportList" resultType="${basePackage}.${entityVoPackage}.${className}ExportVo">
        select
        ${infos}
        from ${tableName}
        <include refid="query_where"/>
    </select>


    <sql id="query_where">
        <where>
            <#if columns??>
                <#list columns as column>
                    <#if column.name = 'create_by' || column.name = 'create_date' || column.name = 'update_by' || column.name = 'update_date' || column.name = 'remarks' || column.name = 'del_flag'>
                    <#else>
            <if test="po.${column.fields} != null ">
                and  ${column.name} = ${wellNo}po.${column.fields}}
            </if>
                    </#if>
                </#list>
            </#if>
        </where>
    </sql>

</mapper>