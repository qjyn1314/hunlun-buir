package com.hulunbuir.evening.generationcode.controller;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.ds.GroupDataSource;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.hulunbuir.common.base.QueryRequest;
import com.hulunbuir.common.config.ApplicationUtil;
import com.hulunbuir.common.config.BuirProperties;
import com.hulunbuir.evening.controller.BaseController;
import com.hulunbuir.evening.generationcode.entity.CodeTable;
import com.hulunbuir.evening.generationcode.entity.Column;
import com.hulunbuir.evening.generationcode.service.GenerationService;
import com.hulunbuir.evening.generationcode.util.CodeHelper;
import com.hulunbuir.evening.generationcode.util.GenerationConfig;
import com.hulunbuir.parent.exception.HulunBuirException;
import com.hulunbuir.parent.result.JsonResult;
import com.hulunbuir.parent.tool.CommonUtils;
import com.hulunbuir.parent.tool.FileUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * explain: 用于生成代码的控制层
 * 思路，将生成代码的配置存放到redis中，key的生成根据不同的模板来进行生成
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/1 14:17
 */
@Slf4j
@RestController
@RequestMapping("/generation")
public class GenerationController extends BaseController {

    @Autowired
    private GenerationService generationService;
    @Autowired
    private CodeHelper generatorHelper;

    @ApiOperation("保存配置")
    @PostMapping("/saveGeneration")
    public JsonResult saveGeneration(GenerationConfig generationConfig) {
        generationConfig.setSessionId(getSessionId());
        return JsonResult.success(generationService.saveGeneration(generationConfig));
    }

    @ApiOperation("获取已配置的模板文件夹列表")
    @GetMapping("/getFolderList")
    public JsonResult getFolderList() {
        final File file;
        try {
            ClassPathResource resource = new ClassPathResource("generation" + File.separator);
            file = resource.getFile();
            return JsonResult.success(Arrays.stream(Objects.requireNonNull(file.listFiles())).map(folder -> folder.getPath().substring(folder.getPath().lastIndexOf("\\") + 1)).collect(Collectors.toList()));
        } catch (IOException e) {
            log.error("获取已配置的模板文件夹列表-失败", e);
            return JsonResult.error("获取已配置的模板文件夹列表失败。");
        }
    }

    @ApiOperation("获取配置")
    @GetMapping("/getGeneration")
    public JsonResult getGeneration() {
        GenerationConfig generationConfig = new GenerationConfig(getSessionId());
        return JsonResult.success(generationService.getGeneration(generationConfig));
    }

    @ApiOperation("数据库列表")
    @GetMapping("/databases")
    public JsonResult databases() {
        return JsonResult.success(generationService.databases());
    }

    /**
     * 获取sessionId，即关闭当前浏览器会有新的sessionId
     *
     * @author wangjunming
     * @since 2020/7/8 13:12
     */
    private String getSessionId() {
        return ApplicationUtil.getRequestSessionId();
    }

    @ApiOperation("数据库中的表")
    @GetMapping("/tables")
    @DS("p2p-test")
    public Map<String, Object> tables(QueryRequest queryRequest, CodeTable generation) {
        return getLayTable(generationService.tables(queryRequest, generation));
    }

    @ApiOperation("生成代码并下载")
    @GetMapping("/generationCodeDownload")
    public void generationCodeDownload(@RequestParam String schemaName, @RequestParam String tableName, HttpServletRequest request, HttpServletResponse response) throws HulunBuirException {
        log.info("生成代码的当前de 数据库是：{}，表名是：{}", schemaName, tableName);
        GenerationConfig config = new GenerationConfig(getSessionId());
        config = generationService.getGeneration(config);
        if (StringUtils.isBlank(config.getAuthor())) {
            HulunBuirException.build("请配置生成代码的设置！！");
        }
        CodeTable generation = new CodeTable(schemaName, tableName);
        generation = generationService.tablesOne(generation);
        final String className = CommonUtils.underscoreToCamel(tableName);
        log.info("生成的表名是：{}", className);
        final String remark = generation.getRemark();
        config.setTableName(tableName);
        config.setClassName(className);
        config.setTableComment(remark);
        List<Column> columns = generationService.getColumns(generation);
        try {
            final File file = new File(BuirProperties.me().getTemplateGenerationTmp() + "src");
            final boolean mkdirs = file.mkdirs();
            log.info("创建临时文件夹是否成功：{}", mkdirs);
        } catch (Exception e) {
            log.error("创建临时文件夹失败！", e);
        }
        try {
            if (GenerationConfig.DEFAULT_FOLDER.equals(config.getTemplateFolder())) {
                generatorHelper.generateEntityFile(columns, config);
                generatorHelper.generateMapperFile(GenerationConfig.MAPPER_FILE_SUFFIX, config);
                generatorHelper.generateMapperXmlFile(config);
                generatorHelper.generateServiceFile(columns, config, true);
                generatorHelper.generateServiceImplFile(columns, config);
                generatorHelper.generateControllerFile(columns, config);
            } else if (GenerationConfig.SLC_FOLDER.equals(config.getTemplateFolder())) {
                String entity = GenerationConfig.ENTITY_TEMPLATE, vo = "infoVo.ftl", exportVo = "exportVo.ftl", pageListPo = "pageListPo.ftl", po = "po.ftl", pageListVo = "pageListVo.ftl";
                generatorHelper.generateEntityVoPoFile(columns, config, config.getEntityPackage(), entity);
                generatorHelper.generateEntityVoPoFile(columns, config, config.getEntityPoPackage(), po);
                generatorHelper.generateEntityVoPoFile(columns, config, config.getEntityVoPackage(), vo);
                generatorHelper.generateEntityVoPoFile(columns, config, config.getEntityVoPackage(), exportVo);
                generatorHelper.generateEntityVoPoFile(columns, config, config.getEntityVoPackage(), pageListVo);
                generatorHelper.generateEntityVoPoFile(columns, config, config.getEntityPoPackage(), pageListPo);
                generatorHelper.generateControllerFile(columns, config);
                generatorHelper.generateServiceFile(columns, config, false);
                generatorHelper.generateMapperFile(GenerationConfig.MAPPER_FILE_SUFFIX, config);
                generatorHelper.generateMapperCrudXmlFile(columns, config);
            }
        } catch (Exception e) {
            log.error("生成代码失败", e);
        }
        // 打包
        String zipFile = System.currentTimeMillis() + GenerationConfig.SUFFIX;
        try {
            FileUtil.compress(BuirProperties.me().getTemplateGenerationTmp() + "src", zipFile);
            // 下载
            FileUtil.download(zipFile, tableName + GenerationConfig.SUFFIX, true, response);
        } catch (Exception e) {
            log.error("下载文件失败：", e);
        }
        // 删除临时目录
        FileUtil.delete(BuirProperties.me().getTemplateGenerationTmp());
    }

}