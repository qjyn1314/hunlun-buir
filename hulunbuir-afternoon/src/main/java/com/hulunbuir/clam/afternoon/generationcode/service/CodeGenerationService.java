package com.hulunbuir.clam.afternoon.generationcode.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.clam.afternoon.generationcode.CodeGenerationConfig;
import com.hulunbuir.clam.afternoon.generationcode.entity.CodeGeneration;
import com.hulunbuir.clam.common.base.QueryRequest;

import java.util.List;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/4 16:15
 */
public interface CodeGenerationService {
    /**
     * 获取数据库列表
     *
     * @author wangjunming
     * @since 2020/7/5 17:41
     */
    List<String> databases();

    /**
     * 获取相应数据库中的所有表
     *
     * @author wangjunming
     * @since 2020/7/5 17:45
     */
    IPage<CodeGeneration> tables(QueryRequest queryRequest, CodeGeneration generation);


    /**
     * 保存生成代码的配置
     *
     * @author wangjunming
     * @since 2020/7/8 10:16
     */
    Boolean saveGeneration(CodeGenerationConfig generationConfig);

    /**
     * 根据sessionId获取生成代码的配置
     *
     * @author wangjunming
     * @since 2020/7/8 13:14
     */
    CodeGenerationConfig getGeneration(CodeGenerationConfig codeGenerationConfig);


}
