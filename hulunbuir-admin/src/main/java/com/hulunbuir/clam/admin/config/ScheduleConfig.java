package com.hulunbuir.clam.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 定时任务配置信息
 *
 * @author wangjunming
 * @since 2020/5/13 14:08
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(scheduleTaskExecutor());
    }

    @Bean(destroyMethod="shutdown")
    public ExecutorService scheduleTaskExecutor() {
        return Executors.newScheduledThreadPool(5);
    }
}
