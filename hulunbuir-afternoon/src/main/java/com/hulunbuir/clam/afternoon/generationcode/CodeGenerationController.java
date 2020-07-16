package com.hulunbuir.clam.afternoon.generationcode;

import com.hulunbuir.clam.afternoon.generationcode.entity.CodeGeneration;
import com.hulunbuir.clam.afternoon.generationcode.entity.Column;
import com.hulunbuir.clam.afternoon.generationcode.service.CodeGenerationService;
import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.common.config.ApplicationContextUtils;
import com.hulunbuir.clam.common.utils.CommonUtils;
import com.hulunbuir.clam.common.utils.FileUtil;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import com.hulunbuir.clam.parent.result.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

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
public class CodeGenerationController extends BaseController {

    @Autowired
    private CodeGenerationService codeGenerationService;
    @Autowired
    private CodeGeneratorHelper generatorHelper;

    public static void main(String[] args) throws FileNotFoundException {
        String resourcesFolder = "/generation/";
        String templatePath = CodeGeneratorHelper.class.getResource(resourcesFolder).getPath();
        System.out.println(templatePath);
        File file =  new  File(templatePath);
        final String[] list = file.list();

        System.out.println(Arrays.toString(list));


    }

    @ApiOperation("保存配置")
    @PostMapping("/saveGeneration")
    public JsonResult saveGeneration(CodeGenerationConfig generationConfig) {
        generationConfig.setSessionId(getSessionId());
        log.info("生成代码配置是：{}", generationConfig);
        return JsonResult.success(codeGenerationService.saveGeneration(generationConfig));
    }

    @ApiOperation("获取已配置的模板文件夹列表")
    @GetMapping("/getFolderList")
    public JsonResult getFolderList() {
        File fileDir = null;
        String[] folderList = null;
        try {
            String resourcesFolder = "/generation/";
            String templatePath = CodeGeneratorHelper.class.getResource(resourcesFolder).getPath();
            fileDir = new File(templatePath);
            folderList = fileDir.list();
        } catch (Exception e) {
            log.error("获取已配置模板的问价列表失败！！", e);
            folderList = new String[]{};
        }
        assert folderList != null;
        return JsonResult.success(Arrays.asList(folderList));
    }

    @ApiOperation("获取配置")
    @GetMapping("/getGeneration")
    public JsonResult getGeneration() {
        CodeGenerationConfig codeGenerationConfig = new CodeGenerationConfig(getSessionId());
        return JsonResult.success(codeGenerationService.getGeneration(codeGenerationConfig));
    }

    @ApiOperation("数据库列表")
    @GetMapping("/databases")
    public JsonResult databases() {
        return JsonResult.success(codeGenerationService.databases());
    }

    /**
     * 获取sessionId，即关闭当前浏览器会有新的sessionId
     *
     * @author wangjunming
     * @since 2020/7/8 13:12
     */
    private String getSessionId() {
        return ApplicationContextUtils.getRequestSessionId();
    }

    @ApiOperation("数据库中的表")
    @GetMapping("/tables")
    public JsonResult tables(QueryRequest queryRequest, CodeGeneration generation) {
        return JsonResult.success(getDataTable(codeGenerationService.tables(queryRequest, generation)));
    }

    @ApiOperation("生成代码并下载")
    @GetMapping("/generationCodeDownload")
    public void generationCodeDownload(@RequestParam String schemaName, @RequestParam String tableName, HttpServletRequest request, HttpServletResponse response) throws HulunBuirException {
        log.info("生成代码的当前de 数据库是：{}，表名是：{}", schemaName, tableName);
        CodeGenerationConfig codeGenerationConfig = new CodeGenerationConfig(getSessionId());
        codeGenerationConfig = codeGenerationService.getGeneration(codeGenerationConfig);
        if (StringUtils.isBlank(codeGenerationConfig.getAuthor())) {
            throw HulunBuirException.build("请配置生成代码的设置！！");
        }
        CodeGeneration generation = new CodeGeneration(schemaName, tableName);
        generation = codeGenerationService.tablesOne(generation);
        final String className = CommonUtils.underscoreToCamel(tableName);
        log.info("生成的表名是：{}", className);
        final String remark = generation.getRemark();
        codeGenerationConfig.setTableName(tableName);
        codeGenerationConfig.setClassName(className);
        codeGenerationConfig.setTableComment(remark);
        List<Column> columns = codeGenerationService.getColumns(generation);
        try {
            generatorHelper.generateEntityFile(columns, codeGenerationConfig);
            generatorHelper.generateMapperFile(columns, codeGenerationConfig);
            generatorHelper.generateMapperXmlFile(columns, codeGenerationConfig);
            generatorHelper.generateServiceFile(columns, codeGenerationConfig);
            generatorHelper.generateServiceImplFile(columns, codeGenerationConfig);
            generatorHelper.generateControllerFile(columns, codeGenerationConfig);
        } catch (Exception e) {
            log.error("生成代码失败", e);
        }
        // 打包
        String zipFile = System.currentTimeMillis() + CodeGenerationConfig.SUFFIX;
        try {
            FileUtil.compress(CodeGenerationConfig.TEMP_PATH + "src", zipFile);
            // 下载
            FileUtil.download(zipFile, tableName + CodeGenerationConfig.SUFFIX, true, response);
        } catch (Exception e) {
            log.error("下载文件失败：", e);
        }
        // 删除临时目录
        FileUtil.delete(CodeGenerationConfig.TEMP_PATH);

    }



}