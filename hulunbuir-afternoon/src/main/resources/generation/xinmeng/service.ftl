package ${basePackage}.${servicePackage};

import com.wisea.cloud.common.constants.ConstantError;
import com.wisea.cloud.common.pojo.ResultPoJo;
import com.wisea.cloud.common.service.BaseService;
import com.wisea.cloud.common.util.ConverterUtil;
import com.wisea.cloud.common.util.LoggerUtil;
import com.wisea.cloud.model.Page;
import com.wisea.cloud.model.po.LongIdPo;
import ${basePackage}.${mapperPackage}.${className}Mapper;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${entityPoPackage}.${className}PageListPo;
import ${basePackage}.${entityPoPackage}.${className}Po;
import ${basePackage}.${entityVoPackage}.${className}InfoVo;
import ${basePackage}.${entityVoPackage}.${className}PageListVo;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wbf-coder-generator
 * @version 1.0
 * @className ${className}Service
 * @date ${date}
 * @Description ${tableComment} 控制层
 */
@Service
public class ${className}Service extends BaseService {

    @Autowired
    private ${className}Mapper mapper;

    /**
     * @author wbf-coder-generator
     * @date ${date}
     * @Description 分页查询${className}-${tableComment}
     */
    public ResultPoJo<Page<${className}PageListVo>> findPageList(${className}PageListPo po) {
        LoggerUtil.infoWithContext("【findPageList】分页查询列表：", po);
        ResultPoJo<Page<${className}PageListVo>> resultPoJo = new ResultPoJo<>(ConstantError.NOMAL);
        Page<${className}PageListVo> page = po.getPage();
        page.setList(mapper.findPageList(po));
        resultPoJo.setResult(page);
        return resultPoJo;
    }

    /**
     * @author wbf-coder-generator
     * @date ${date}
     * @Description 逻辑删除${className}-${tableComment}
     */
    @Transactional
    public ResultPoJo logicDel(LongIdPo po) {
        LoggerUtil.infoWithContext("【logicDel】逻辑删除：", po);
        ResultPoJo resultPoJo = new ResultPoJo<>(ConstantError.NOMAL);
        ${className} entity = mapper.selectByPrimaryKey(po.getId());
        if(ConverterUtil.isNotEmpty(entity)){
            entity.setDelFlag(${className}.DEL_FLAG_DELETE);
            entity.preUpdate();
            mapper.updateByPrimaryKey(entity);
        }else{
            resultPoJo.setCode(ConstantError.ERR_004);
            resultPoJo.setMsg(ConstantError.MSG_004);
        }
        return resultPoJo;
    }

    /**
     * @author wbf-coder-generator
     * @date ${date}
     * @Description 新增或修改${className}-${tableComment}
     */
    @Transactional
    public ResultPoJo saveOrUpdate(${className}Po po) {
        LoggerUtil.infoWithContext("【saveOrUpdate】新增或修改：", po);
        ResultPoJo resultPoJo = new ResultPoJo<>(ConstantError.NOMAL);
        if(ConverterUtil.isNotEmpty(po.getId())){
            //修改
            ${className} entity = mapper.selectByPrimaryKey(po.getId());
            if(ConverterUtil.isNotEmpty(entity)){
                BeanUtils.copyProperties(po, entity);
                entity.preUpdate();
                mapper.updateByPrimaryKeySelective(entity);
            }else{
                resultPoJo.setCode(ConstantError.ERR_004);
                resultPoJo.setMsg(ConstantError.MSG_004);
            }
        }else{
            //新增
            ${className} entity = new ${className}();
            BeanUtils.copyProperties(po, entity);
            entity.preInsert();
            mapper.insert(entity);
        }
        return resultPoJo;
    }

    /**
     * @author wbf-coder-generator
     * @date ${date}
     * @Description 查询详细信息${className}-${tableComment}
     */
    public ResultPoJo<${className}InfoVo> findInfo(LongIdPo po) {
        LoggerUtil.infoWithContext("【findInfo】查询详细信息：", po);
        ResultPoJo<${className}InfoVo> resultPoJo = new ResultPoJo<>(ConstantError.NOMAL);
        ${className}InfoVo vo = mapper.findInfo(po);
        if(ConverterUtil.isNotEmpty(vo)){
            resultPoJo.setResult(vo);
        }else{
            resultPoJo.setCode(ConstantError.ERR_004);
            resultPoJo.setMsg(ConstantError.MSG_004);
        }
        return resultPoJo;
    }

    /**
     * @author wbf-coder-generator
     * @date ${date}
     * @Description 查询详细信息${className}-${tableComment}
     */
    public ResultPoJo<${className}InfoVo> findInfoes(${className}Po po) {
        LoggerUtil.infoWithContext("【findInfo】查询详细信息：", po);
        ResultPoJo<${className}InfoVo> resultPoJo = new ResultPoJo<>(ConstantError.NOMAL);
        ${className}InfoVo vo = mapper.findInfoes(po);
        if(ConverterUtil.isNotEmpty(vo)){
            resultPoJo.setResult(vo);
        }else{
            resultPoJo.setCode(ConstantError.ERR_004);
            resultPoJo.setMsg(ConstantError.MSG_004);
        }
        return resultPoJo;
    }

}