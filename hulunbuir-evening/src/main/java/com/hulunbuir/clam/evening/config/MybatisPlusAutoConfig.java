package com.hulunbuir.clam.evening.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Explain:mtbatis-plus的配置信息
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-17 11:50
 */
@Configuration
@MapperScan(basePackages = {
        "com.hulunbuir.clam.evening.*.mapper",
},annotationClass = Repository.class)
public class MybatisPlusAutoConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        page.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        page.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        page.setCountSqlParser(new JsqlParserCountOptimize(true));
        return page;
    }

}