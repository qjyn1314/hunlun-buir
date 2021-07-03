package com.hulunbuir.datasource;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.hulunbuir.datasource.config.DynamicDataSourceProperties;
import com.hulunbuir.datasource.config.JdbcDynamicDataSourceProvider;
import com.hulunbuir.datasource.config.LastParamDsProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 此数据源参考了pig-cloud开源项目，就是直接复制拿过来的。
 *
 * @author wangjunming
 * @since 2020/10/30 9:20
 */
@Slf4j
@DependsOn("applicationUtil")
@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DynamicDataSourceAutoConfiguration {

    @Autowired
    private DynamicDataSourceProperties dynamicDataSourceProperties;

    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        log.info("主数据源的配置信息是：{}", dynamicDataSourceProperties);
        return new JdbcDynamicDataSourceProvider(dynamicDataSourceProperties);
    }

    @Bean
    public DsProcessor dsProcessor() {
        return new LastParamDsProcessor();
    }

}
