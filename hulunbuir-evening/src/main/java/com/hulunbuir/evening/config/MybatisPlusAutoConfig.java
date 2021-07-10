//package com.hulunbuir.evening.config;
//
//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///**
// * <p>
// * Explain:mtbatis-plus的配置信息
// * </p >
// *
// * @author wangjunming
// * @since 2020-01-17 11:50
// */
//@Configuration
//@MapperScan(basePackages = {
//        "com.hulunbuir.evening.*.mapper",
//},annotationClass = Repository.class)
//@EnableTransactionManagement
//public class MybatisPlusAutoConfig {
//    @Bean
//    public MybatisPlusInterceptor paginationInterceptor() {
//        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
//        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor());
//        return mybatisPlusInterceptor;
//    }
//
//    @Bean
//    public PaginationInnerInterceptor paginationInnerInterceptor() {
//        return new PaginationInnerInterceptor();
//    }
//
//}
