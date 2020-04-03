package com.hulunbuir.clam.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    @Bean(name = "druidDataSource",destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        log.info("当前工程的数据源信息：{}", druidDataSource);
        return druidDataSource;
    }
//
//    /**
//     * 注入seata数据源
//     * Seata 是通过代理数据源实现分布式事务，所以需要先配置一个数据源的代理，否则事务不会回滚。
//     *
//     * @param druidDataSource:
//     * @return io.seata.rm.datasource.DataSourceProxy
//     * @author wangjunming
//     * @since 2020/1/21 18:52
//     */
//    @Bean
//    @Primary
//    public DataSourceProxy dataSourceProxy(@Qualifier("druidDataSource") DruidDataSource druidDataSource) {
//        DataSourceProxy dataSourceProxy = new DataSourceProxy(druidDataSource);
//        log.info("当前工程的Seata数据源信息：{}", dataSourceProxy);
//        return dataSourceProxy;
//    }
//
//    /**
//     * 项目名称
//     *
//     * @author wangjunming
//     * @since 2020/4/2 16:14
//     */
//    @Value("${spring.application.name}")
//    private String applicationId;
//
//    /**
//     * 分布式的分组ID
//     *
//     * @author wangjunming
//     * @since 2020/4/3 11:56
//     */
//    @Value("${alibaba.seata.tx-service-group}")
//    private String txServiceGroup;
//
//    /**
//     * init global transaction scanner
//     *
//     * @author wangjunming
//     * @since 2020/3/29 10:52
//     */
//    @Bean
//    public GlobalTransactionScanner globalTransactionScanner() {
//        GlobalTransactionScanner globalTransactionScanner = new GlobalTransactionScanner(applicationId, txServiceGroup);
//        log.info("当前工程的Seata-GlobalTransactionScanner：{}", globalTransactionScanner);
//        return globalTransactionScanner;
//    }

}
