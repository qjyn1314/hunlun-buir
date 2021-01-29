package com.hulunbuir.admin;

import com.hulunbuir.datasource.annotation.EnableDynamicDataSource;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableDubbo
@EnableScheduling
@EnableAdminServer
@SpringBootApplication
@EnableDynamicDataSource
@ComponentScan(basePackages = {"com.hulunbuir"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}