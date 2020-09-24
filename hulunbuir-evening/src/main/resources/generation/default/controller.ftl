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
    private I${className}Service service;

    @ApiOperation("${tableComment}分页列表")
    @GetMapping("/page")
    public JsonResult page(QueryRequest queryRequest, ${className} ${className?uncap_first}){
        return JsonResult.success(getDataTable(service.page(queryRequest,${className?uncap_first})));
    }

    @ApiOperation("${tableComment}添加")
    @PostMapping("/save")
    public JsonResult save(${className} ${className?uncap_first}){
        return JsonResult.success(service.save(${className?uncap_first}));
    }

    @ApiOperation("${tableComment}修改")
    @PostMapping("/update")
    public JsonResult update(${className} ${className?uncap_first}){
        return JsonResult.success(service.update(${className?uncap_first}));
    }

    @ApiOperation("${tableComment}获取")
    @GetMapping("/selOne")
    public JsonResult selOne(${className} ${className?uncap_first}){
        return JsonResult.success(service.selOne(${className?uncap_first}));
    }

}
