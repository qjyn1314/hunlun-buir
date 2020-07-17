package ${basePackage}.${servicePackage};

import com.wisea.cloud.common.pojo.ResultPoJo;
import com.wisea.cloud.common.service.BaseService;
import com.wisea.cloud.model.Page;
import ${basePackage}.${mapperPackage}.${className}Dao;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${entityPoPackage}.*;
import ${basePackage}.${entityVoPackage}.${className}Vo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${tableComment} Service接口
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${className}Service extends BaseService {

    @Autowired
    private ${className}Dao ${className?uncap_first}Dao;

   /**
    * 封装返回参数
    *
    * @author ${author}
    * @since ${date}
    */
    public static <T> ResultPoJo<T> getResult(T t) {
        ResultPoJo<T> result = new ResultPoJo<T>();
        result.setResult(t);
        result.setMsg("success");
        return result;
    }

   /**
    * 获得分页列表
    *
    * @author ${author}
    * @since ${date}
    */
    public ResultPoJo<Page<${className}>> ${className?uncap_first}Page(${className}PagePo page${className}Po) {
        Page<${className}> ${className?uncap_first}PoPage = page${className}Po.getPage();
        ${className?uncap_first}PoPage.setList(${className?uncap_first}Dao.findPage(page${className}Po));
        return getResult(${className?uncap_first}PoPage);
    }

   /**
    * 添加
    *
    * @author ${author}
    * @since ${date}
    */
    @Transactional
    public ResultPoJo<Boolean> save${className}(${className}InsertPo ${className?uncap_first}InsertPo) {
        ${className} ${className?uncap_first} = new ${className}();
        BeanUtils.copyProperties(${className?uncap_first}InsertPo,${className?uncap_first});
        return getResult(${className?uncap_first}Dao.insert(${className?uncap_first})==1);
    }

   /**
    * 修改
    *
    * @author ${author}
    * @since ${date}
    */
    @Transactional
    public ResultPoJo<Boolean> update${className}(${className}UpdatePo ${className?uncap_first}UpdatePo) {
        ${className} ${className?uncap_first} = new ${className}();
        BeanUtils.copyProperties(${className?uncap_first}UpdatePo, ${className?uncap_first});
        return getResult(${className?uncap_first}Dao.update(${className?uncap_first})==1);
    }

   /**
    * 获取单个
    *
    * @author ${author}
    * @since ${date}
    */
    public ResultPoJo<${className}Vo> getOne${className}(${className}Po ${className?uncap_first}Po) {
        return getResult(${className?uncap_first}Dao.getOne${className}(${className?uncap_first}Po));
    }

   /**
    * 通过ID获取单个
    *
    * @author ${author}
    * @since ${date}
    */
    public ResultPoJo<${className}Vo> getOne${className}ById(${className}IdPo ${className?uncap_first}IdPo) {
        return getResult(${className?uncap_first}Dao.selectByPrimaryKey(${className?uncap_first}IdPo));
    }

}