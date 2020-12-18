package com.hulunbuir.evening.generationcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.evening.generationcode.entity.CodeTable;
import com.hulunbuir.evening.generationcode.entity.Column;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/4 16:16
 */
@Repository
public interface GenerationMapper extends BaseMapper<CodeTable> {

    /**
     * 获取数据库列表
     * @author wangjunming
     * @since 2020/7/5 17:42
     */
    List<String> generationDatabases();

    /**
     * 获取数据库中的所有表
     *
     * @author wangjunming
     * @since 2020/7/5 17:47
     */
    IPage<CodeTable> generationTables(Page<CodeTable> page, @Param("generation") CodeTable generation);

    /**
     * 根据数据库名称和表名获取数据表的详细信息
     *
     * @author wangjunming
     * @since 2020/7/14 17:19
     */
    CodeTable tablesOne(@Param("generation") CodeTable generation);


    /**
     * 获取所选表的所有列
     *
     * @author wangjunming
     * @since 2020/7/14 17:30
     */
    List<Column> getColumns(@Param("generation") CodeTable generation);

}
