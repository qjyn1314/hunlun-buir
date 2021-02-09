package com.hulunbuir.admin.springstudy.txconfig;

import com.baomidou.dynamic.datasource.creator.DruidDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/8 21:26
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.hulunbuir.admin.springstudy.txconfig")
public class TxConfig {

    /**
     * 自定义druid数据源
     * @return DataSource
     */
    @Bean
    public DataSource txMasterDataSource() {
        DruidConfig druidConfig = new DruidConfig();
        DruidDataSourceCreator druidDataSourceCreator = new DruidDataSourceCreator(druidConfig);
        DataSourceProperty sourceProperty = new DataSourceProperty();
        sourceProperty.setDriverClassName(dbMessageInfo().driverClassName);
        sourceProperty.setUrl(dbMessageInfo().url);
        sourceProperty.setUsername(dbMessageInfo().username);
        sourceProperty.setPassword(dbMessageInfo().password);
        return druidDataSourceCreator.createDataSource(sourceProperty);
    }

    /**
     * 自定义mapper层，可操作数据库
     * @return JdbcTemplate
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(txMasterDataSource());
    }

    /**
     * 数据库链接信息
     * @return DbMessageInfo
     */
    @Bean
    public DbMessageInfo dbMessageInfo() {
        return new DbMessageInfo();
    }

    /**
     * 自定义spring事务管理器
     * @return PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(txMasterDataSource());
    }


}
