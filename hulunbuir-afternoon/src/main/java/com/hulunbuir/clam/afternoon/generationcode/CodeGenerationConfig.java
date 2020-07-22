package com.hulunbuir.clam.afternoon.generationcode;

import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/1 14:45
 */
@Data
public class CodeGenerationConfig implements Serializable {

    /**
     * 存放在redis中的会话ID，用于获取当前操作人所生成的配置信息
     */
    private String sessionId;
    /**
     * 生成的压缩文件后缀名
     */
    public static final String SUFFIX = "_code.zip";
    /**
     * 基础包名
     */
    @Size(min = 3, max = 50, message = "请输入基础包名，包名不得超过50个字符")
    private String basePackage;
    /**
     * java类型文件后缀
     */
    public static final String JAVA_FILE_SUFFIX = ".java";
    /**
     * mapper文件类型后缀
     */
    public static final String MAPPER_FILE_SUFFIX = "Mapper.java";
    /**
     * service文件类型后缀
     */
    public static final String SERVICE_FILE_SUFFIX = "Service.java";
    /**
     * service impl文件类型后缀
     */
    public static final String SERVICEIMPL_FILE_SUFFIX = "ServiceImpl.java";
    /**
     * mapper xml文件存放路径
     */
    @Size(min = 3, max = 20, message = "请输入mapper xml文件存放路径，包名不得超过20个字符")
    private String mapperXmlPackage;
    /**
     * servcie文件存放路径
     */
    @Size(min = 3, max = 20, message = "请输入servcie文件存放路径，包名不得超过20个字符")
    private String servicePackage;
    /**
     * serviceImpl文件存放路径
     */
    @Size(min = 3, max = 20, message = "请输入serviceImpl文件存放路径，包名不得超过20个字符")
    private String serviceImplPackage;
    /**
     * controller文件存放路径
     */
    @Size(min = 3, max = 20, message = "请输入controller文件存放路径，包名不得超过20个字符")
    private String controllerPackage;
    /**
     * java文件路径，固定值
     */
    private String javaPath = "/src/main/java/";
    /**
     * 配置文件存放路径，固定值
     */
    private String resourcesPath = "src/main/resources";
    /**
     * controller文件类型后缀
     */
    public static final String CONTROLLER_FILE_SUFFIX = "Controller.java";
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表注释
     */
    private String tableComment;
    /**
     * 数据表对应的类名
     */
    private String className;
    /**
     * mapper xml文件类型后缀
     */
    public static final String MAPPERXML_FILE_SUFFIX = "Mapper.xml";
    /**
     * entity模板
     */
    public static final String ENTITY_TEMPLATE = "entity.ftl";
    /**
     * mapper模板
     */
    public static final String MAPPER_TEMPLATE = "mapper.ftl";
    /**
     * service接口模板
     */
    public static final String SERVICE_TEMPLATE = "service.ftl";
    /**
     * service impl接口模板
     */
    public static final String SERVICEIMPL_TEMPLATE = "serviceImpl.ftl";
    /**
     * controller接口模板
     */
    public static final String CONTROLLER_TEMPLATE = "controller.ftl";
    /**
     * mapper xml接口模板
     */
    public static final String MAPPERXML_TEMPLATE = "mapperXml.ftl";
    /**
     * 模板文件夹
     */
    @Size(min = 3, max = 20, message = "请输入模板文件夹，作者名称不能超过20个字符")
    private String templateFolder;
    /**
     * 作者
     */
    @Size(min = 3, max = 20, message = "请输入作者名称，作者名称不能超过20个字符")
    private String author;
    /**
     * entity文件存放路径-即数据库对应的实体类
     */
    @Size(min = 3, max = 20, message = "请输入entity文件存放路径，包名不得超过20个字符")
    private String entityPackage;
    /**
     * entityPo文件存放路径-即提交传参使用
     */
    @Size(min = 3, max = 20, message = "请输入entityPo文件存放路径，包名不得超过20个字符")
    private String entityPoPackage;
    /**
     * entityVo文件存放路径-即回显传参使用
     */
    @Size(min = 3, max = 20, message = "请输入entityVo文件存放路径，包名不得超过20个字符")
    private String entityVoPackage;
    /**
     * mapper文件存放路径
     */
    @Size(min = 3, max = 20, message = "请输入mapper文件存放路径，包名不得超过20个字符")
    private String mapperPackage;
    /**
     * 文件生成日期
     */
    private String date = DateUtils.formatFullTime(LocalDateTime.now(), DateUtils.FULL_TIME_SPLIT_PATTERN);

    public static final String defaultFolder = "default";
    public static final String xinMengFolder = "xinmeng";

    public CodeGenerationConfig() {
            this.templateFolder = xinMengFolder;
            this.author = "coder";
            this.basePackage = "com.wisea.qdcy.tp.product";
            this.entityPackage = "entity";
            this.mapperPackage = "dao";
            this.mapperXmlPackage = "mappings";
            this.servicePackage = "service";
            this.entityPoPackage = "po";
            this.entityVoPackage = "vo";
            this.controllerPackage = "controller";
    }

    public CodeGenerationConfig(String sessionId) {
        this.sessionId = sessionId;
    }

}
