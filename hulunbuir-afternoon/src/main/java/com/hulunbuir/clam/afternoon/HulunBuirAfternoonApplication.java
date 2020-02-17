package com.hulunbuir.clam.afternoon;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;

@ComponentScan(basePackages = {
        "com.hulunbuir.clam.afternoon",
        "com.hulunbuir.clam.parent",
        "com.hulunbuir.clam.common",
        "com.hulunbuir.clam.route.config",
        "com.hulunbuir.clam.distributed",
})
@EnableDubboConfiguration
@EnableScheduling
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
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();
        }
    }

    /**
     * 定时任务
     *
     * @author wangjunming
     * @since 2020/2/12 21:11
     * @return void
     */
//    @Scheduled(cron = "0/10 * * * * ?")
//    public void run20s() {
//        log.info("定时任务:{}", LocalDateTime.now());
//    }

}
