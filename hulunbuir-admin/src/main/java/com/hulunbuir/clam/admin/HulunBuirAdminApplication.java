package com.hulunbuir.clam.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@ComponentScan(basePackages = {"com.hulunbuir.clam"})
@EnableDubbo
@Configuration
@EnableAdminServer
@SpringBootApplication
public class HulunBuirAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(HulunBuirAdminApplication.class, args);
    }
}
