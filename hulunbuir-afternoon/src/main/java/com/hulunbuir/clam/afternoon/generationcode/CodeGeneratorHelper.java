package com.hulunbuir.clam.afternoon.generationcode;

import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import com.hulunbuir.clam.afternoon.generationcode.entity.Column;
import com.hulunbuir.clam.common.utils.CommonUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 生成代码工具类，将创建freemarker模板类，封装数据，生成文件
 *
 * @author wangjunming
 * @since 2020/7/14 18:49
 */
@Slf4j
@Component
public class CodeGeneratorHelper {

    @Value("${service.template.generation}")
    private String templateGenerationFolder;
    @Value("${service.template.generation.tmp}")
    private String templateGenerationTmpFolder;

    private static JSONObject toJSONObject(Object o) {
        return JSONObject.parseObject(JSONObject.toJSON(o).toString());
    }

    @SuppressWarnings("UnstableApiUsage")
    private void generateFileByTemplate(String templateFolder, String templateName, File file, Object data) throws Exception {
        Template template = getTemplate(templateFolder, templateName);
        Files.createParentDirs(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try (Writer out = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8), 10240)) {
            template.process(data, out);
        } catch (Exception e) {
            String message = "代码生成异常";
            log.error(message, e);
            throw new Exception(message);
        }
    }

    private String getFilePath(CodeGenerationConfig configure, String packagePath, String suffix, boolean serviceInterface) {
        String filePath = templateGenerationTmpFolder + configure.getJavaPath() +
                packageConvertPath(configure.getBasePackage() + "." + packagePath);
        if (serviceInterface) {
            filePath += "I";
        }
        filePath += configure.getClassName() + suffix;
        return filePath;
    }

    private String getFilePath(CodeGenerationConfig configure, String packagePath, String suffix, String entityVoPoClassName) {
        String filePath = templateGenerationTmpFolder + configure.getJavaPath() +
                packageConvertPath(configure.getBasePackage() + "." + packagePath);
        filePath += configure.getClassName() + entityVoPoClassName + suffix;
        return filePath;
    }

    private String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    private Template getTemplate(String templateFolder, String templateName) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        String resourcesFolder = "/generation/" + templateFolder + "/";
        String templatePath = CodeGeneratorHelper.class.getResource(resourcesFolder).getPath();
        File file = new File(templatePath);
        if (!file.exists()) {
            templatePath = templateGenerationFolder + templateFolder + "/";
        }
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return configuration.getTemplate(templateName);
    }

//-------------------------------本项目
    public void generateEntityFile(List<Column> columns, CodeGenerationConfig configure) throws Exception {
        String suffix = CodeGenerationConfig.JAVA_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getEntityPackage(), suffix, false);
        String templateName = CodeGenerationConfig.ENTITY_TEMPLATE;
        File entityFile = new File(path);
        JSONObject data = toJSONObject(configure);
        data.put("hasDate", false);
        data.put("hasBigDecimal", false);
        columns.forEach(c -> {
            if (StringUtils.containsAny(c.getType(), "date", "datetime", "timestamp")) {
                data.put("hasDate", true);
            }
            if (StringUtils.containsAny(c.getType(), "decimal", "numeric")) {
                data.put("hasBigDecimal", true);
            }
        });
        data.put("columns", columns);
        generateFileByTemplate(configure.getTemplateFolder(), templateName, entityFile, data);
    }

    public void generateMapperFile(String suffix, CodeGenerationConfig configure) throws Exception {
        String path = getFilePath(configure, configure.getMapperPackage(), suffix, false);
        String templateName = CodeGenerationConfig.MAPPER_TEMPLATE;
        File mapperFile = new File(path);
        generateFileByTemplate(configure.getTemplateFolder(), templateName, mapperFile, toJSONObject(configure));
    }

    public void generateServiceFile(List<Column> columns, CodeGenerationConfig configure,boolean serviceInterface) throws Exception {
        String suffix = CodeGenerationConfig.SERVICE_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getServicePackage(), suffix, serviceInterface);
        String templateName = CodeGenerationConfig.SERVICE_TEMPLATE;
        File serviceFile = new File(path);
        generateFileByTemplate(configure.getTemplateFolder(), templateName, serviceFile, toJSONObject(configure));
    }

    public void generateServiceImplFile(List<Column> columns, CodeGenerationConfig configure) throws Exception {
        String suffix = CodeGenerationConfig.SERVICEIMPL_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getServiceImplPackage(), suffix, false);
        String templateName = CodeGenerationConfig.SERVICEIMPL_TEMPLATE;
        File serviceImplFile = new File(path);
        generateFileByTemplate(configure.getTemplateFolder(), templateName, serviceImplFile, toJSONObject(configure));
    }

    public void generateControllerFile(List<Column> columns, CodeGenerationConfig configure) throws Exception {
        String suffix = CodeGenerationConfig.CONTROLLER_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getControllerPackage(), suffix, false);
        String templateName = CodeGenerationConfig.CONTROLLER_TEMPLATE;
        File controllerFile = new File(path);
        generateFileByTemplate(configure.getTemplateFolder(), templateName, controllerFile, toJSONObject(configure));
    }

    public void generateMapperXmlFile(CodeGenerationConfig configure) throws Exception {
        String suffix = CodeGenerationConfig.MAPPERXML_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getMapperXmlPackage(), suffix, false);
        String templateName = CodeGenerationConfig.MAPPERXML_TEMPLATE;
        File mapperXmlFile = new File(path);
        generateFileByTemplate(configure.getTemplateFolder(), templateName, mapperXmlFile, toJSONObject(configure));
    }

//------------------------------------------新盟风控

    public void generateEntityVoPoFile(List<Column> columns, CodeGenerationConfig configure, String entityVoPoPackage, String templateName) throws Exception {
        String suffix = CodeGenerationConfig.JAVA_FILE_SUFFIX;
        String path = null;
        if(CodeGenerationConfig.ENTITY_TEMPLATE.equals(templateName)){
            path =  getFilePath(configure, entityVoPoPackage, suffix, false);
        }else{
            String entityVoPoClassName = CommonUtils.upperFirstLatter(templateName.substring(0, templateName.indexOf(".")));
            path =  getFilePath(configure, entityVoPoPackage, suffix, entityVoPoClassName);
        }
        File entityFile = new File(path);
        JSONObject data = toJSONObject(configure);
        data.put("hasBigDecimal", false);
        data.put("hasOffsetDateTime", false);
        columns.forEach(c -> {
            if (StringUtils.containsAny(c.getType(), "decimal", "numeric")) {
                data.put("hasBigDecimal", true);
            }
            if (StringUtils.containsAny(c.getType(), "date", "datetime", "timestamp")) {
                data.put("hasOffsetDateTime", true);
            }
        });
        data.put("columns", columns);
        generateFileByTemplate(configure.getTemplateFolder(), templateName, entityFile, data);
    }

    public void generateMapperCrudXmlFile(List<Column> columns, CodeGenerationConfig configure) throws Exception {
        String suffix = CodeGenerationConfig.MAPPERXML_FILE_SUFFIX;
        String path = getXmlCrudFilePath(configure, configure.getMapperXmlPackage(), suffix);
        String templateName = CodeGenerationConfig.MAPPERXML_TEMPLATE;
        File mapperXmlFile = new File(path);
        JSONObject data = toJSONObject(configure);
        data.put("columns", columns);
        data.put("columnsCommaSeparated", getColumnsCommaSeparated(columns));
        data.put("insertSetValue", getColumnsSetValuesSeparatedByInsert(columns));
        data.put("idStr",getIdStrs(columns));
        data.put("wellNo", "#{");
        data.put("updateByPrimaryKey", getUpdateByPrimaryKey(columns));
        data.put("infos", findInfos(columns));
        generateFileByTemplate(configure.getTemplateFolder(), templateName, mapperXmlFile, data);
    }

    //where条件中的ID
    private String getIdStrs(List<Column> columns) {
        String idStr = "id";
        StringBuilder builder = new StringBuilder();
        columns.forEach(column -> {
            if(idStr.equals(column.getName())){
                builder.append("#{").append(column.getFields()).append(",jdbcType=").append(column.getTypeCapital()).append("}");
            }
        });
        return builder.toString();
    }

    //获取crud的xmlPath，放在sources目录下
    private String getXmlCrudFilePath(CodeGenerationConfig configure, String packagePath, String suffix ) {
        String filePath = templateGenerationTmpFolder + packageConvertPath(configure.getResourcesPath() + "." + packagePath);
        filePath += configure.getClassName() + suffix;
        return filePath;
    }

    //全量插入数据时使用到的values值
    private String getColumnsSetValuesSeparatedByInsert(List<Column> columns) {
        StringBuilder columnNameSetValue = new StringBuilder();
        columns.forEach(column -> {
           columnNameSetValue.append("#{").append(column.getFields()).append(",jdbcType=").append(column.getTypeCapital()).append("}, ");
        });
        return columnNameSetValue.toString().substring(0,columnNameSetValue.length()-2);
    }

    //更新时以ID作为条件更新时的setValue值
    private String getUpdateByPrimaryKey(List<Column> columns) {
        StringBuilder columnNameSetValue = new StringBuilder();
        columns.forEach(column -> {
            columnNameSetValue.append(column.getName()).append(" = #{").append(column.getFields()).append(",jdbcType=").append(column.getTypeCapital()).append("},");
        });
        return columnNameSetValue.toString().substring(0,columnNameSetValue.length()-1);
    }

    //更新时以ID作为条件更新时的setValue值
    private String findInfos(List<Column> columns) {
        StringBuilder columnNameSetValue = new StringBuilder();
        String[] exColumns = {"create_by","create_date","update_by","update_date","remarks","del_flag"};
        columns.forEach(column -> {
            if(!Arrays.asList(exColumns).contains(column.getName())){
                columnNameSetValue.append(column.getName()).append(" ").append(column.getFields()).append(", ");
            }
        });
        return columnNameSetValue.toString().substring(0,columnNameSetValue.length()-2);
    }

    //列名以逗号分隔的字符串
    private String getColumnsCommaSeparated(List<Column> columns){
        return columns.stream().map(Column::getName).collect(Collectors.joining(","));
    }

}