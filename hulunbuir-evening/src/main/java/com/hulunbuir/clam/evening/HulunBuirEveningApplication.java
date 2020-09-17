package com.hulunbuir.clam.evening;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@EnableDubbo
@ComponentScan(basePackages = {"com.hulunbuir.clam","com.calm.security"})
@SpringBootApplication
public class HulunBuirEveningApplication {
    public static void main(String[] args) {
        SpringApplication.run(HulunBuirEveningApplication.class, args);
    }
}
