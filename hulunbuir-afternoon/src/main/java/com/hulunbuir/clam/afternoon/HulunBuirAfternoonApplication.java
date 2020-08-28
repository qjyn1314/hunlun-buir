package com.hulunbuir.clam.afternoon;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@EnableDubbo
@ComponentScan("com.hulunbuir.clam")
@SpringBootApplication
public class HulunBuirAfternoonApplication {
    public static void main(String[] args) {
        SpringApplication.run(HulunBuirAfternoonApplication.class, args);
    }
}
