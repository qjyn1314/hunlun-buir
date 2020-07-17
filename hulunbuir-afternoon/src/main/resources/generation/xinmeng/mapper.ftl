package ${basePackage}.${mapperPackage};

import com.wisea.cloud.common.mybatis.persistence.CrudDao;
import com.wisea.cloud.model.po.PagePo;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${entityPoPackage}.${className}IdPo;
import ${basePackage}.${entityPoPackage}.${className}Po;
import ${basePackage}.${entityVoPackage}.${className}Vo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${tableComment} Dao
 *
 * @author ${author}
 * @since ${date}
 */
@Repository
public interface ${className}Dao extends CrudDao<${className}>{

   /**
    * 分页列表
    *
    * @author ${author}
    * @since ${date}
    */
    List<${className}> findPage(@Param("${className?uncap_first}") PagePo<${className}> ${className?uncap_first}Po);

   /**
    * 保存
    *
    * @author ${author}
    * @since ${date}
    */
    int insert(${className} ${className?uncap_first});

   /**
    * 修改
    *
    * @author ${author}
    * @since ${date}
    */
    int update(${className} ${className?uncap_first});

   /**
    * 获取单个
    *
    * @author ${author}
    * @since ${date}
    */
    ${className}Vo getOne${className}(@Param("${className?uncap_first}") ${className}Po ${className?uncap_first}Po);


   /**
    * 通过ID获取单个
    *
    * @author ${author}
    * @since ${date}
    */
    ${className}Vo selectByPrimaryKey(${className}IdPo ${className?uncap_first}IdPo);

}