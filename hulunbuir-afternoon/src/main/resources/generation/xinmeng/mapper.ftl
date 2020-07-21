package ${basePackage}.${mapperPackage};

import com.wisea.cloud.common.mybatis.persistence.CrudDao;
import com.wisea.cloud.model.po.LongIdPo;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${entityPoPackage}.${className}PageListPo;
import ${basePackage}.${entityPoPackage}.${className}Po;
import ${basePackage}.${entityVoPackage}.${className}InfoVo;
import ${basePackage}.${entityVoPackage}.${className}PageListVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ${tableComment} Mapper
 * @since ${date}
 */
@Mapper
@Repository
public interface ${className}Mapper extends CrudDao<${className}>{

   /**
    * @author wbf-coder-generator
    * @date ${date}
    * @Description 分页查询${className}-${tableComment}
    */
    List<${className}PageListVo> findPageList(${className}PageListPo po);

   /**
    * @author wbf-coder-generator
    * @date ${date}
    * @Description 查询详细信息${className}-${tableComment}
    */
    ${className}InfoVo findInfo(LongIdPo po);

   /**
    * @author wbf-coder-generator
    * @date ${date}
    * @Description 查询详细信息${className}-${tableComment}
    */
    ${className}InfoVo findInfoes(@Param("po")${className}Po po);

}