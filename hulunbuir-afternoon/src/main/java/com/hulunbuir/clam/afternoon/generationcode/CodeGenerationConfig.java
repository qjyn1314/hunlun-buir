package com.hulunbuir.clam.afternoon.generationcode;

import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

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
@Component
public class CodeGenerationConfig implements Serializable {

    /**
     * 存放在redis中的会话ID，用于获取当前操作人所生成的配置信息
     */
    private String sessionId;
    /**
     * 作者
     */
    @Size(min = 3, max = 20, message = "请输入作者名称，作者名称不能超过20个字符")
    private String author;
    /**
     * 基础包名
     */
    @Size(min = 3, max = 50, message = "请输入基础包名，包名不得超过50个字符")
    private String basePackage;
    /**
     * entity文件存放路径
     */
    @Size(min = 3, max = 20, message = "请输入entity文件存放路径，包名不得超过20个字符")
    private String entityPackage;
    /**
     * mapper文件存放路径
     */
    @Size(min = 3, max = 20, message = "请输入mapper文件存放路径，包名不得超过20个字符")
    private String mapperPackage;
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
     * 文件生成日期
     */
    private String fileCreatedDate = DateUtils.formatFullTime(LocalDateTime.now(), DateUtils.FULL_TIME_SPLIT_PATTERN);
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

    public CodeGenerationConfig() {
    }

    public CodeGenerationConfig(String sessionId) {
        this.sessionId = sessionId;
    }

}
