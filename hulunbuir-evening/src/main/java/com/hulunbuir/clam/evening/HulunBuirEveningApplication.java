package com.hulunbuir.clam.evening;

import com.calm.datasource.annotation.EnableDynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@EnableDubbo
@SpringBootApplication
@EnableDynamicDataSource
@ComponentScan(basePackages = {"com.hulunbuir.clam", "com.calm.security"})
public class HulunBuirEveningApplication {
    public static void main(String[] args) {
        SpringApplication.run(HulunBuirEveningApplication.class, args);
    }
}
