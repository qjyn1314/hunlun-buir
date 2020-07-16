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
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    public void generateMapperFile(List<Column> columns, CodeGenerationConfig configure) throws Exception {
        String suffix = CodeGenerationConfig.MAPPER_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getMapperPackage(), suffix, false);
        String templateName = CodeGenerationConfig.MAPPER_TEMPLATE;
        File mapperFile = new File(path);
        generateFileByTemplate(configure.getTemplateFolder(), templateName, mapperFile, toJSONObject(configure));
    }

    public void generateServiceFile(List<Column> columns, CodeGenerationConfig configure) throws Exception {
        String suffix = CodeGenerationConfig.SERVICE_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getServicePackage(), suffix, true);
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

    public void generateMapperXmlFile(List<Column> columns, CodeGenerationConfig configure) throws Exception {
        String suffix = CodeGenerationConfig.MAPPERXML_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getMapperXmlPackage(), suffix, false);
        String templateName = CodeGenerationConfig.MAPPERXML_TEMPLATE;
        File mapperXmlFile = new File(path);
        JSONObject data = toJSONObject(configure);
        columns.forEach(c -> c.setField(CommonUtils.underscoreToCamel(StringUtils.lowerCase(c.getName()))));
        data.put("columns", columns);
        generateFileByTemplate(configure.getTemplateFolder(), templateName, mapperXmlFile, data);
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

    private String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    private Template getTemplate(String templateFolder, String templateName) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        String resourcesFolder = "/generation/" + templateFolder + "/";
        String templatePath = CodeGeneratorHelper.class.getResource(resourcesFolder).getPath();
        File file = new File(templatePath);
        configuration.setDirectoryForTemplateLoading(file);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return configuration.getTemplate(templateName);
    }

}