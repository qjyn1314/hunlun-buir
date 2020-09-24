package ${basePackage}.${servicePackage};

import com.baomidou.mybatisplus.core.metadata.IPage;
import ${basePackage}.${entityPackage}.${className};
import com.hulunbuir.clam.common.base.QueryRequest;

/**
 * ${tableComment} Service接口
 *
 * @author ${author}
 * @since ${date}
 */
public interface I${className}Service {

    /**
     * ${tableComment}分页列表
     *
     * @author ${author}
     * @since ${date}
     */
    IPage<${className}> page(QueryRequest queryRequest, ${className} ${className?uncap_first});

    /**
     * 保存
     *
     * @author ${author}
     * @since ${date}
     */
     boolean save(${className} ${className?uncap_first});

    /**
     * 修改
     *
     * @author ${author}
     * @since ${date}
     */
     boolean update(${className} ${className?uncap_first});


    /**
     * 获取单个
     *
     * @author ${author}
     * @since ${date}
     */
    ${className} selOne(${className} ${className?uncap_first});


}
