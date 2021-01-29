//package com.hulunbuir.evening.config;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.activiti.spring.SpringAsyncExecutor;
//import org.activiti.spring.SpringProcessEngineConfiguration;
//import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
//import java.io.IOException;
//
///**
// * <p>
// * explain:  声名为配置类，继承Activiti抽象配置类
// *
// *
// * </p>
// *
// * @author wangjunming
// * @since 2021/1/15 18:49
// */
//@Slf4j
//@Configuration
//public class ActivitiAutoConfig extends AbstractProcessEngineAutoConfiguration {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @PostConstruct
//    public void init(){
//        log.info("数据源是：{}",dataSource);
//        log.info("数据源是：{}", JSONObject.toJSONString(dataSource));
//    }
//
//
//    @Bean
//    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
//            PlatformTransactionManager transactionManager,
//            SpringAsyncExecutor springAsyncExecutor) throws IOException {
//
//        return baseSpringProcessEngineConfiguration(
//                dataSource,
//                transactionManager,
//                springAsyncExecutor);
//    }
//}
