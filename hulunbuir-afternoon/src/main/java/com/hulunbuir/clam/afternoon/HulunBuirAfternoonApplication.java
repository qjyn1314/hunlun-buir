package com.hulunbuir.clam.afternoon;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.hulunbuir.clam.afternoon",
        "com.hulunbuir.clam.parent",
        "com.hulunbuir.clam.common",
        "com.hulunbuir.clam.route.config",
        "com.hulunbuir.clam.distributed",
})
@EnableDubboConfiguration
@SpringBootApplication
@Slf4j
public class HulunBuirAfternoonApplication {
    public static void main(String[] args) {
        log.info("开始启动-->{}", DateUtils.getDateTimes());
        SpringApplication.run(HulunBuirAfternoonApplication.class, args);
        log.info("启动成功-->{}", DateUtils.getDateTimes());
    }
}
