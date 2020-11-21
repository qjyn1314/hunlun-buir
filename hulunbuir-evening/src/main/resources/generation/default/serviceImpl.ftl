package ${basePackage}.${serviceImplPackage};

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${mapperPackage}.${className}Mapper;
import ${basePackage}.${servicePackage}.I${className}Service;
import com.hulunbuir.clam.common.base.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * ${tableComment} Service实现
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
public class ${className}ServiceImpl implements I${className}Service {

    @Autowired
    private ${className}Mapper ${className?uncap_first}Mapper;

   /**
    * ${tableComment}分页列表
    *
    * @param queryRequest
    * @param ${className?uncap_first}
    * @author ${author}
    * @since ${date}
    */
    @Override
    public IPage<${className}> page(QueryRequest queryRequest, ${className} ${className?uncap_first}) {
        LambdaQueryWrapper<${className}> queryWrapper = initQueryWrapper(queryRequest,${className?uncap_first});
        Page<${className}> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return ${className?uncap_first}Mapper.selectPage(page, queryWrapper);
    }

    /**
    * 列表的查询参数
    *
    * @author ${author}
    * @since ${date}
    */
    private LambdaQueryWrapper<${className}> initQueryWrapper(QueryRequest queryRequest, ${className} ${className?uncap_first}) {
        LambdaQueryWrapper<${className}> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

   /**
    * 保存
    *
    * @param ${className?uncap_first}
    * @author ${author}
    * @since ${date}
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(${className} ${className?uncap_first}) {
        //--TODO 做一些初始化动作
        return ${className?uncap_first}Mapper.insert(${className?uncap_first})>0;
    }

   /**
    * 修改
    *
    * @param ${className?uncap_first}
    * @author ${author}
    * @since ${date}
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(${className} ${className?uncap_first}) {
        //--TODO 做一些效验动作
        return ${className?uncap_first}Mapper.updateById(${className?uncap_first})>0;
    }

   /**
    * 获取单个
    *
    * @param ${className?uncap_first}
    * @author ${author}
    * @since ${date}
    */
    @Override
    public ${className} selOne(${className} ${className?uncap_first}) {
    LambdaQueryWrapper<${className}> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO 初始化查询条件
        return ${className?uncap_first}Mapper.selectOne(queryWrapper);
    }


}
