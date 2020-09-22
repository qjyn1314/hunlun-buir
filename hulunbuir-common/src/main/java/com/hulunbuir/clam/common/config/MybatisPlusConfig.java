package com.hulunbuir.clam.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * Explain:mtbatis-plus的配置信息
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-17 11:50
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {
//        "com.hulunbuir.clam.*.*.mapper",
        "com.hulunbuir.clam.evening.generationcode.mapper",
        "com.hulunbuir.clam.evening.persistence.mapper",
})
public class MybatisPlusConfig {

}
