package ${basePackage}.${controllerPackage};

import com.wisea.cloud.common.annotation.DataCheck;
import com.wisea.cloud.common.pojo.ResultPoJo;
import com.wisea.cloud.model.Page;
import com.wisea.cloud.model.po.LongIdPo;
import ${basePackage}.${entityPoPackage}.${className}PageListPo;
import ${basePackage}.${entityPoPackage}.${className}Po;
import ${basePackage}.${servicePackage}.${className}Service;
import ${basePackage}.${entityVoPackage}.${className}InfoVo;
import ${basePackage}.${entityVoPackage}.${className}PageListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wbf-coder-generator
 * @version 1.0
 * @className ${className}Controller
 * @date ${date}
 * @Description ${tableComment} 控制层
 */
@Api(tags = "${tableComment}web调用接口-${className}Controller")
@RestController
@RequestMapping(value = "/w/${className}")
public class ${className}Controller  {

    @Autowired
    private ${className}Service service;

    /**
     * @author wbf-coder-generator
     * @date ${date}
     * @Description 分页查询${className}-${tableComment}
     */
    @DataCheck
    @RequestMapping(value = "/findPageList", method = RequestMethod.POST)
    @ApiOperation(nickname = "分页查询列表-${tableComment}", value = "分页查询列表", notes = "分页查询列表", httpMethod = "POST")
    public ResultPoJo<Page<${className}PageListVo>> findPageList(@RequestBody ${className}PageListPo po) {
        return service.findPageList(po);
    }

    /**
     * @author wbf-coder-generator
     * @date ${date}
     * @Description 逻辑删除${className}-${tableComment}
     */
    @DataCheck
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(nickname = "逻辑删除-${tableComment}", value = "逻辑删除", notes = "逻辑删除", httpMethod = "POST")
    public ResultPoJo<Boolean> logicDel(@RequestBody LongIdPo po) {
        return service.logicDel(po);
    }

    /**
     * @author wbf-coder-generator
     * @date ${date}
     * @Description 新增或修改${className}-${tableComment}
     */
    @DataCheck
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ApiOperation(nickname = "新增或修改-${tableComment}", value = "新增或修改", notes = "新增或修改", httpMethod = "POST")
    public ResultPoJo saveOrUpdate(@RequestBody ${className}Po po) {
        return service.saveOrUpdate(po);
    }

    /**
     * @author wbf-coder-generator
     * @date ${date}
     * @Description 查询详细信息${className}-${tableComment}
     */
    @DataCheck
    @RequestMapping(value = "/findInfo", method = RequestMethod.POST)
    @ApiOperation(nickname = "查询详细信息ById-${tableComment}", value = "查询详细信息", notes = "查询详细信息", httpMethod = "POST")
    public ResultPoJo<${className}InfoVo> findInfo(@RequestBody LongIdPo po) {
        return service.findInfo(po);
    }

    /**
     * @author wbf-coder-generator
     * @date ${date}
     * @Description 查询详细信息${className}-${tableComment}
     */
    @RequestMapping(value = "/findInfoes", method = RequestMethod.POST)
    @ApiOperation(nickname = "查询详细信息多个条件-${tableComment}", value = "查询详细信息", notes = "查询详细信息", httpMethod = "POST")
    public ResultPoJo<${className}InfoVo> findInfoes(@RequestBody ${className}Po po) {
        return service.findInfoes(po);
    }

}