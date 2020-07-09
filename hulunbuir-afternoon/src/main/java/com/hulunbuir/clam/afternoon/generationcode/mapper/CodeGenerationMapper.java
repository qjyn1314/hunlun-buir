package com.hulunbuir.clam.afternoon.generationcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.afternoon.generationcode.entity.CodeGeneration;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
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
public interface CodeGenerationMapper extends BaseMapper<T> {

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
    IPage<CodeGeneration> generationTables(Page<CodeGeneration> page, @Param("generation") CodeGeneration generation);


}
