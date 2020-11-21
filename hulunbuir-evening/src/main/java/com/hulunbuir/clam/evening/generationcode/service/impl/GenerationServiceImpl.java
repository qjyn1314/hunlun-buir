package com.hulunbuir.clam.evening.generationcode.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.evening.generationcode.util.GenerationConfig;
import com.hulunbuir.clam.evening.generationcode.entity.CodeTable;
import com.hulunbuir.clam.evening.generationcode.entity.Column;
import com.hulunbuir.clam.evening.generationcode.mapper.GenerationMapper;
import com.hulunbuir.clam.evening.generationcode.service.GenerationService;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.common.config.RedisService;
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
public class GenerationServiceImpl implements GenerationService {

    @Autowired
    private GenerationMapper generationMapper;
    @Autowired
    private RedisService redisService;
    /**
     * 获取数据库列表
     *
     * @author wangjunming
     * @since 2020/7/5 17:41
     */
    @Override
    public List<String> databases() {
        return generationMapper.generationDatabases();
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
    public IPage<CodeTable> tables(QueryRequest queryRequest, CodeTable generation) {
        Page<CodeTable> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return generationMapper.generationTables(page,generation);
    }

    /**
     * 保存生成代码的配置
     *
     * @param generationConfig
     * @author wangjunming
     * @since 2020/7/8 10:16
     */
    @Override
    public Boolean saveGeneration(GenerationConfig generationConfig) {
        redisService.setStrKey(generationConfig.getSessionId(),generationConfig,3600);
        return redisService.getStrValue(generationConfig.getSessionId()) != null;
    }

    /**
     * 根据sessionId获取生成代码的配置
     *
     * @param generationConfig
     * @author wangjunming
     * @since 2020/7/8 13:14
     */
    @Override
    public GenerationConfig getGeneration(GenerationConfig generationConfig) {
        final Object configStrValue = redisService.getStrValue(generationConfig.getSessionId());
        return null == configStrValue ? new GenerationConfig() : JSONObject.parseObject(JSON.toJSON(configStrValue).toString(), GenerationConfig.class);
    }

    /**
     * 根据数据库名称和表名获取数据表的详细信息
     *
     * @param generation 数据表名+数据库名
     * @author wangjunming
     * @since 2020/7/14 17:18
     */
    @Override
    public CodeTable tablesOne(CodeTable generation) {
        return generationMapper.tablesOne(generation);
    }

    /**
     * 获取所选表的所有列
     *
     * @param generation
     * @author wangjunming
     * @since 2020/7/14 17:30
     */
    @Override
    public List<Column> getColumns(CodeTable generation) {
        return generationMapper.getColumns(generation);
    }
}
