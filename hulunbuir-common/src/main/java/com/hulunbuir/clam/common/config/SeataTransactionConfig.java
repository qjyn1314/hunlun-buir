package com.hulunbuir.clam.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.GlobalTransactionScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * <p>
 * Explain:分布式事务的配置类
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-21 18:39
 */
@Slf4j
@Configuration
public class SeataTransactionConfig {

    /**
     * 创建数据源
     *
     * @return javax.sql.DataSource
     * @author wangjunming
     * @since 2020/1/23 15:25
     */
    @Bean("druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 注入seata数据源
     * Seata 是通过代理数据源实现分布式事务，所以需要先配置一个数据源的代理，否则事务不会回滚。
     *
     * @param druidDataSource:
     * @return io.seata.rm.datasource.DataSourceProxy
     * @author wangjunming
     * @since 2020/1/21 18:52
     */
    @Bean
    @Primary
    public DataSourceProxy dataSourceProxy(@Qualifier("druidDataSource") DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    /**
     * 创建分布式事务的扫描
     *
     * @return io.seata.spring.annotation.GlobalTransactionScanner
     * @author wangjunming
     * @since 2020/1/23 15:26
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSourceProxy dataSourceProxy) {
        return new DataSourceTransactionManager(dataSourceProxy);
    }

}
