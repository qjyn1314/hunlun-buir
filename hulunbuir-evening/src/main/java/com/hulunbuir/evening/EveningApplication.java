package com.hulunbuir.evening;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
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
@EnableDubbo
@SpringBootApplication
@ComponentScan(basePackages = {"com.hulunbuir"})
public class EveningApplication {
    public static void main(String[] args) {
        SpringApplication.run(EveningApplication.class, args);
        log.info("Start_Success !!!");
    }
}
