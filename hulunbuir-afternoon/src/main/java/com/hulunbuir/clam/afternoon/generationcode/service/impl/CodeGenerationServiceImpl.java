package com.hulunbuir.clam.afternoon.generationcode.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.afternoon.generationcode.CodeGenerationConfig;
import com.hulunbuir.clam.afternoon.generationcode.entity.CodeGeneration;
import com.hulunbuir.clam.afternoon.generationcode.entity.Column;
import com.hulunbuir.clam.afternoon.generationcode.mapper.CodeGenerationMapper;
import com.hulunbuir.clam.afternoon.generationcode.service.CodeGenerationService;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.common.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/4 16:16
 */
@Slf4j
@Service
public class CodeGenerationServiceImpl implements CodeGenerationService {

    @Autowired
    private CodeGenerationMapper codeGenerationMapper;
    @Autowired
    private RedisConfig redisConfig;
    /**
     * 获取数据库列表
     *
     * @author wangjunming
     * @since 2020/7/5 17:41
     */
    @Override
    public List<String> databases() {
        return codeGenerationMapper.generationDatabases();
    }

    /**
     * 获取相应数据库中的所有表
     *
     * @param queryRequest
     * @param generation
     * @author wangjunming
     * @since 2020/7/5 17:45
     */
    @Override
    public IPage<CodeGeneration> tables(QueryRequest queryRequest, CodeGeneration generation) {
        Page<CodeGeneration> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return codeGenerationMapper.generationTables(page,generation);
    }

    /**
     * 保存生成代码的配置
     *
     * @param generationConfig
     * @author wangjunming
     * @since 2020/7/8 10:16
     */
    @Override
    public Boolean saveGeneration(CodeGenerationConfig generationConfig) {
        redisConfig.setStrKey(generationConfig.getSessionId(),generationConfig,3600);
        return redisConfig.getStrValue(generationConfig.getSessionId()) != null;
    }

    /**
     * 根据sessionId获取生成代码的配置
     *
     * @param codeGenerationConfig
     * @author wangjunming
     * @since 2020/7/8 13:14
     */
    @Override
    public CodeGenerationConfig getGeneration(CodeGenerationConfig codeGenerationConfig) {
        final Object configStrValue = redisConfig.getStrValue(codeGenerationConfig.getSessionId());
        return null == configStrValue ? new CodeGenerationConfig() : JSONObject.parseObject(JSON.toJSON(configStrValue).toString(), CodeGenerationConfig.class);
    }

    /**
     * 根据数据库名称和表名获取数据表的详细信息
     *
     * @param generation 数据表名+数据库名
     * @author wangjunming
     * @since 2020/7/14 17:18
     */
    @Override
    public CodeGeneration tablesOne(CodeGeneration generation) {
        return codeGenerationMapper.tablesOne(generation);
    }

    /**
     * 获取所选表的所有列
     *
     * @param generation
     * @author wangjunming
     * @since 2020/7/14 17:30
     */
    @Override
    public List<Column> getColumns(CodeGeneration generation) {
        return codeGenerationMapper.getColumns(generation);
    }
}
