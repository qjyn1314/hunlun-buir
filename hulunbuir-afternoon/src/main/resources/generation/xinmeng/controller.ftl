package ${basePackage}.${controllerPackage};

import com.wisea.cloud.common.pojo.ResultPoJo;
import com.wisea.cloud.model.Page;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${entityPoPackage}.*;
import ${basePackage}.${servicePackage}.${className}Service;
import ${basePackage}.${entityVoPackage}.${className}Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * explain: ${tableComment} Controller
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Api(tags = "${tableComment}web调用接口")
@RestController
@RequestMapping(value = "/w")
public class ${className}Controller  {

    @Autowired
    private ${className}Service ${className?uncap_first}Service;

    @ApiOperation(value = "分页列表-${tableComment}", notes = "获取分页列表")
    @GetMapping("/${className?uncap_first}Page")
    public ResultPoJo<Page<${className}>> ${className?uncap_first}Page(${className}PagePo page${className}Po) {
        return ${className?uncap_first}Service.${className?uncap_first}Page(page${className}Po);
    }

    @ApiOperation(value = "添加-${tableComment}", notes = "添加")
    @GetMapping("/save${className}")
    public ResultPoJo<Boolean> save${className}(${className}InsertPo ${className?uncap_first}InsertPo) {
        return ${className?uncap_first}Service.save${className}(${className?uncap_first}InsertPo);
    }

    @ApiOperation(value = "修改-${tableComment}", notes = "修改")
    @GetMapping("/update${className}")
    public ResultPoJo<Boolean> update${className}(${className}UpdatePo ${className?uncap_first}UpdatePo) {
        return ${className?uncap_first}Service.update${className}(${className?uncap_first}UpdatePo);
    }

    @ApiOperation(value = "单个查询-${tableComment}", notes = "单个查询")
    @GetMapping("/getOne${className}")
    public ResultPoJo<${className}Vo> getOne${className}(${className}Po ${className?uncap_first}Po) {
        return ${className?uncap_first}Service.getOne${className}(${className?uncap_first}Po);
    }

    @ApiOperation(value = "单个查询ById-${tableComment}", notes = "单个查询ById")
    @GetMapping("/getOne${className}ById")
    public ResultPoJo<${className}Vo> getOne${className}ById(${className}IdPo ${className?uncap_first}IdPo) {
        return ${className?uncap_first}Service.getOne${className}ById(${className?uncap_first}IdPo);
    }

}