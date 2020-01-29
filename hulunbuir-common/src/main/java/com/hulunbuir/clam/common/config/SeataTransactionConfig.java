package com.hulunbuir.clam.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.GlobalTransactionScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

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
     * @author wangjunming
     * @since 2020/1/23 15:25
     * @return javax.sql.DataSource
     */
    /*@Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }*/

    /**
     * 注入seata数据源
     * Seata 是通过代理数据源实现事务分支，所以需要先配置一个数据源的代理，否则事务不会回滚。
     *
     * @param druidDataSource:
     * @return io.seata.rm.datasource.DataSourceProxy
     * @author wangjunming
     * @since 2020/1/21 18:52
     */
   /* @Bean
    @Primary
    public DataSourceProxy dataSourceProxy(DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }
*/
    /*@Bean
//    @ConfigurationProperties(prefix = "mybatis")
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DruidDataSource druidDataSource) {
        // 这里用 MybatisSqlSessionFactoryBean 代替了 SqlSessionFactoryBean，否则 MyBatisPlus 不会生效
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(druidDataSource);
        return mybatisSqlSessionFactoryBean;
    }
*/

    /*@Value("${seata.application-id}")
    private String applicationId;
    @Value("${seata.tx-service-group}")
    private String txServiceGroup;

    *//**
     * 创建分布式事务的扫描
     *
     * @return io.seata.spring.annotation.GlobalTransactionScanner
     * @author wangjunming
     * @since 2020/1/23 15:26
     *//*
    @Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        log.info("applicationId：{}--txServiceGroup：{}",applicationId,txServiceGroup);
        return new GlobalTransactionScanner(applicationId, txServiceGroup);
    }*/

    @Autowired
    private DataSourceProperties dataSourceProperties;

    /**
     * init durid datasource
     *
     * @Return: druidDataSource  datasource instance
     */
    @Bean("druidDataSource")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(dataSourceProperties.getUrl());
        druidDataSource.setUsername(dataSourceProperties.getUsername());
        druidDataSource.setPassword(dataSourceProperties.getPassword());
        druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        druidDataSource.setInitialSize(0);
        druidDataSource.setMaxActive(1800);
        druidDataSource.setMaxWait(6000);
        druidDataSource.setMinIdle(0);
        druidDataSource.setValidationQuery("Select 1 from DUAL");
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(25200000);
        druidDataSource.setRemoveAbandoned(true);
        druidDataSource.setRemoveAbandonedTimeout(1800);
        druidDataSource.setLogAbandoned(true);
        return druidDataSource;
    }

    /**
     * init datasource proxy
     *
     * @Param: druidDataSource  datasource bean instance
     * @Return: DataSourceProxy  datasource proxy
     */
    @Bean
    @Primary
    public DataSourceProxy dataSourceProxy(@Qualifier("druidDataSource")DruidDataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSourceProxy dataSourceProxy) {
        return new DataSourceTransactionManager(dataSourceProxy);
    }

}
