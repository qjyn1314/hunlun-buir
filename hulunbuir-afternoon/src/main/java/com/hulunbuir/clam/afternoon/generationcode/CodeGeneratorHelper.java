package com.hulunbuir.clam.afternoon.generationcode;

import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import com.hulunbuir.clam.afternoon.generationcode.entity.Column;
import com.hulunbuir.clam.common.utils.CommonUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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

    private static JSONObject toJSONObject(Object o) {
        return JSONObject.parseObject(JSONObject.toJSON(o).toString());
    }

    public static void main(String[] args) {
        final String lowerCase = "pagePo.ftl";
        System.out.println(lowerCase);
        final String underscoreToCamel = CommonUtils.upperFirstLatter(lowerCase.substring(0,lowerCase.indexOf(".")));
        System.out.println(underscoreToCamel);

        String str = "#{avatar}, #{createDate}, #{description}, #{email}, NULL,#{isTab}, #{lastLoginTime}, #{password}, #{phone}, #{sex}, #{status}, #{updateDate}, #{userName},";
        final String substring = str.substring(0, str.length() - 1);
        System.out.println(str);
        System.out.println(substring);

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
        String filePath = CodeGenerationConfig.TEMP_PATH + configure.getJavaPath() +
                packageConvertPath(configure.getBasePackage() + "." + packagePath);
        if (serviceInterface) {
            filePath += "I";
        }
        filePath += configure.getClassName() + suffix;
        return filePath;
    }

    private String getFilePath(CodeGenerationConfig configure, String packagePath, String suffix, String entityVoPoClassName) {
        String filePath = CodeGenerationConfig.TEMP_PATH + configure.getJavaPath() +
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
        log.info("templatePath:{}",templatePath);
        File file = new File(templatePath);
        if (!file.exists()) {
            templatePath = System.getProperties().getProperty("java.io.tmpdir");
            file = new File(templatePath + "/" + templateName);
            FileUtils.copyInputStreamToFile(Objects.requireNonNull(ResourceUtils.class.getClassLoader().getResourceAsStream(ResourceUtils.CLASSPATH_URL_PREFIX + resourcesFolder + templateName)), file);
        }
        configuration.setDirectoryForTemplateLoading(file);
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
            c.setField(CommonUtils.underscoreToCamel(StringUtils.lowerCase(c.getName())));
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

    /**
     *
     * suffix 文件名的后缀
     * @author wangjunming
     * @since 2020/7/17 15:15
     */
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

    public void generateMapperXmlFile(List<Column> columns, CodeGenerationConfig configure,String suffix) throws Exception {
        String path = getFilePath(configure, configure.getMapperXmlPackage(), suffix, false);
        String templateName = CodeGenerationConfig.MAPPERXML_TEMPLATE;
        File mapperXmlFile = new File(path);
        JSONObject data = toJSONObject(configure);
        columns.forEach(c -> c.setField(CommonUtils.underscoreToCamel(StringUtils.lowerCase(c.getName()))));
        columns.forEach(c -> c.setFieldes(CommonUtils.lowerFirstLatter(CommonUtils.underscoreToCamel(StringUtils.lowerCase(c.getName())))));
        data.put("columns", columns);
        data.put("columnsCommaSeparated", getColumnsCommaSeparated(columns));
        String insertSetValue = getColumnsSetValuesSeparatedByInsert(columns);
        insertSetValue = insertSetValue.substring(0,insertSetValue.length()-1);
        data.put("insertSetValue", insertSetValue);
        String updateSetValue = getColumnsSetValuesSeparatedByUpdate(columns);
        updateSetValue = updateSetValue.substring(0,updateSetValue.length()-1);
        data.put("updateSetValue", updateSetValue);
        data.put("wellNo", "#{");

        generateFileByTemplate(configure.getTemplateFolder(), templateName, mapperXmlFile, data);
    }

    private String getColumnsSetValuesSeparatedByInsert(List<Column> columns) {
        StringBuilder columnNameSetValue = new StringBuilder();
        columns.forEach(column -> {
            if("id".equals(column.getName())){
                columnNameSetValue.append("NULL,");
            }else{
                columnNameSetValue.append("#{").append(column.getFieldes()).append("}, ");
            }
        });
        String columnNameSetValueStr = columnNameSetValue.toString();
        return columnNameSetValueStr.substring(0,columnNameSetValueStr.length()-1);
    }

    private String getColumnsSetValuesSeparatedByUpdate(List<Column> columns) {
        final Map<String, String> nameFieldMaps = columns.stream().collect(Collectors.toMap(Column::getName, Column::getFieldes));
        StringBuilder columnNameSetValue = new StringBuilder();
        Set<Map.Entry<String, String>> entries = nameFieldMaps.entrySet();
        for (Map.Entry<String, String> nameFieldMap : entries) {
            if("id".equals(nameFieldMap.getKey())){
                continue;
            }
            columnNameSetValue.append(nameFieldMap.getKey()).append(" = ").append("#{").append(nameFieldMap.getValue()).append("}, ");
        }
        String columnNameSetValueStr = columnNameSetValue.toString();
        return columnNameSetValueStr.substring(0,columnNameSetValueStr.length()-1);
    }

    private String getColumnsCommaSeparated(List<Column> columns){
        return columns.stream().map(Column::getName).collect(Collectors.joining(","));
    }

//------------------------------------------新盟风控

    public void generateEntityVoPoFile(List<Column> columns, CodeGenerationConfig configure, String entityVoPoPackage, String templateName) throws Exception {
        String suffix = CodeGenerationConfig.JAVA_FILE_SUFFIX;
        String path = null;
        if(CodeGenerationConfig.ENTITY_TEMPLATE.equals(templateName)){
            path =  getFilePath(configure, entityVoPoPackage, suffix, false);
        }else{
            final String entityVoPoClassName = CommonUtils.upperFirstLatter(templateName.substring(0, templateName.indexOf(".")));
            path =  getFilePath(configure, entityVoPoPackage, suffix, entityVoPoClassName);
        }
        File entityFile = new File(path);
        JSONObject data = toJSONObject(configure);
        data.put("hasDate", false);
        data.put("hasBigDecimal", false);
        data.put("hasOffsetDateTime", false);
        columns.forEach(c -> {
            c.setField(CommonUtils.underscoreToCamel(StringUtils.lowerCase(c.getName())));
            if (StringUtils.containsAny(c.getType(), "date", "datetime", "timestamp")) {
                data.put("hasDate", true);
            }
            if (StringUtils.containsAny(c.getType(), "decimal", "numeric")) {
                data.put("hasBigDecimal", true);
            }
            if (StringUtils.containsAny(c.getField(), "CreateDate", "UpdateDate")) {
                data.put("hasOffsetDateTime", true);
            }
        });
        data.put("columns", columns);
        generateFileByTemplate(configure.getTemplateFolder(), templateName, entityFile, data);
    }

}