package com.hulunbuir.clam.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.GlobalTransactionScanner;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
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

    /**
     * 项目名称
     *
     * @author wangjunming
     * @since 2020/4/2 16:14
     */
    @Value("${spring.application.name}")
    private String applicationId;

    /**
     * init global transaction scanner
     *
     * @author wangjunming
     * @since 2020/3/29 10:52
     */
    @Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        return new GlobalTransactionScanner(applicationId, applicationId);
    }


    /**
     * init mybatis sqlSessionFactory
     *
     * @author wangjunming
     * @since 2020/3/30 12:10
     */
    /*@Bean
    public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceProxy);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:/com/hulunbuir/clam/afternoon/persistence/mapper/xml/*.xml"));
        factoryBean.setTransactionFactory(new JdbcTransactionFactory());
        return factoryBean.getObject();
    }*/

    /*@Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) {
        // 这里用 MybatisSqlSessionFactoryBean 代替了 SqlSessionFactoryBean，否则 MyBatisPlus 不会生效
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSourceProxy);
        return mybatisSqlSessionFactoryBean;
    }*/




}
