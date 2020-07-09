package ${basePackage}.${controllerPackage};

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.FebsResponse;
import cc.mrbird.febs.common.domain.QueryRequest;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.I${className}Service;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * ${tableComment} Controller
 *
 * @author ${author}
 * @date ${date}
 */
@Slf4j
@Validated
@Controller
public class ${className}Controller extends BaseController {

    @Autowired
    private I${className}Service ${className?uncap_first}Service;


    @GetMapping("${className?uncap_first}")
    @ResponseBody
    public FebsResponse getAll${className}s(${className} ${className?uncap_first}) {
        return new FebsResponse().success().data(${className?uncap_first}Service.find${className}s(${className?uncap_first}));
    }

    @GetMapping("${className?uncap_first}/list")
    @ResponseBody
    public FebsResponse ${className?uncap_first}List(QueryRequest request, ${className} ${className?uncap_first}) {
        Map<String, Object> dataTable = getDataTable(this.${className?uncap_first}Service.find${className}s(request, ${className?uncap_first}));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping("${className?uncap_first}")
    @ResponseBody
    public FebsResponse add${className}(@Valid ${className} ${className?uncap_first}) {
        this.${className?uncap_first}Service.create${className}(${className?uncap_first});
        return new FebsResponse().success();
    }

    @GetMapping("${className?uncap_first}/delete")
    @ResponseBody
    public FebsResponse delete${className}(${className} ${className?uncap_first}) {
        this.${className?uncap_first}Service.delete${className}(${className?uncap_first});
        return new FebsResponse().success();
    }

    @PostMapping("${className?uncap_first}/update")
    @ResponseBody
    public FebsResponse update${className}(${className} ${className?uncap_first}) {
        this.${className?uncap_first}Service.update${className}(${className?uncap_first});
        return new FebsResponse().success();
    }

    @PostMapping("${className?uncap_first}/excel")
    @ResponseBody
    public void export(QueryRequest queryRequest, ${className} ${className?uncap_first}, HttpServletResponse response) {
        List<${className}> ${className?uncap_first}s = this.${className?uncap_first}Service.find${className}s(queryRequest, ${className?uncap_first}).getRecords();
        ExcelKit.$Export(${className}.class, response).downXlsx(${className?uncap_first}s, false);
    }
}
