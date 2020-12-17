package com.hulunbuir.clam.evening;

import com.hulunbuir.datasource.annotation.EnableDynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author wangjunming
 * @since 2020/12/5 23:29
 */
@Slf4j
//@EnableDubbo
@SpringBootApplication
@EnableDynamicDataSource
@ComponentScan(basePackages = {"com.hulunbuir","com.calm.security"})
public class HulunBuirEveningApplication {
    public static void main(String[] args) {
        SpringApplication.run(HulunBuirEveningApplication.class, args);
        log.info("Start Success !!!");
    }
}
