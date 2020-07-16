package ${basePackage}.${controllerPackage};

import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.I${className}Service;
import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.parent.result.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ${tableComment} Controller
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Api(tags = "${tableComment} Controller")
@RestController
@RequestMapping("/${className?uncap_first}")
public class ${className}Controller extends BaseController {

    @Autowired
    private I${className}Service ${className?uncap_first}Service;

    @ApiOperation("${tableComment}分页列表")
    @GetMapping("/${className?uncap_first}Page")
    public JsonResult ${className}Page(QueryRequest queryRequest, ${className} ${className?uncap_first}){
        return JsonResult.success(getDataTable(${className?uncap_first}Service.${className?uncap_first}Page(queryRequest,${className?uncap_first})));
    }

    @ApiOperation("${tableComment}添加")
    @PostMapping("/save${className}")
    public JsonResult saveBuirUserThird(${className} ${className?uncap_first}){
        return JsonResult.success(${className?uncap_first}Service.save${className}(${className?uncap_first}));
    }

    @ApiOperation("${tableComment}修改")
    @PostMapping("/update${className}")
    public JsonResult updateBuirUserThird(${className} ${className?uncap_first}){
        return JsonResult.success(${className?uncap_first}Service.update${className}(${className?uncap_first}));
    }

    @ApiOperation("${tableComment}获取")
    @GetMapping("/getOne${className}")
    public JsonResult getOne${className}(${className} ${className?uncap_first}){
        return JsonResult.success(${className?uncap_first}Service.getOne${className}(${className?uncap_first}));
    }

}
