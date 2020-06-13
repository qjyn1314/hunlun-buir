package com.hulunbuir.clam.afternoon;

import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@ComponentScan(basePackages = {
        "com.hulunbuir.clam.afternoon",
        "com.hulunbuir.clam.parent",
        "com.hulunbuir.clam.common.*",
        "com.hulunbuir.clam.route",
        "com.hulunbuir.clam.route.config.jwt",
        "com.hulunbuir.clam.distributed",
})
@EnableDubbo
@SpringBootApplication
@Slf4j
public class HulunBuirAfternoonApplication {
    public static void main(String[] args) {
        log.info("开始启动-->{}", DateUtils.getDateTimes());
        SpringApplication.run(HulunBuirAfternoonApplication.class, args);
        log.info("启动成功-->{}", DateUtils.getDateTimes());
    }
    /**
     * 配置Security不需要登录验证
     * @author wangjunming
     * @since 2020/2/12 21:10
     */
    @Configuration
    public static class SecurityAdminConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //in a frame because it set 'X-Frame-Options' to 'deny'.      为了解决此错误信息
            http.headers().frameOptions().sameOrigin();

            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();
        }
    }
}
