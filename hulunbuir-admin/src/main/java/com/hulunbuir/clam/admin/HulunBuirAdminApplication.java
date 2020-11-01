package com.hulunbuir.clam.admin;

import com.calm.datasource.annotation.EnableDynamicDataSource;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableDubbo
@Configuration
@EnableAdminServer
@SpringBootApplication
@EnableDynamicDataSource
@ComponentScan(basePackages = {"com.hulunbuir.clam"})
public class HulunBuirAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(HulunBuirAdminApplication.class, args);
    }
}
