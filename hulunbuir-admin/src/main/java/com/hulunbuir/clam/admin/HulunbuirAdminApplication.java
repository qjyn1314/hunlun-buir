package com.hulunbuir.clam.admin;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.hulunbuir.clam.parent.tool.DateUtils;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {
        "com.hulunbuir.clam.admin",
        "com.hulunbuir.clam.parent",
        "com.hulunbuir.clam.common",
        "com.hulunbuir.clam.route.config",
        "com.hulunbuir.clam.distributed",
})
@EnableDubboConfiguration
@EnableAdminServer
@Configuration
@Slf4j
@SpringBootApplication
public class HulunbuirAdminApplication {

    public static void main(String[] args) {
        log.info("开始启动-->{}", DateUtils.getDateTimes());
        SpringApplication.run(HulunbuirAdminApplication.class, args);
        log.info("启动结束-->{}", DateUtils.getDateTimes());
    }

}
