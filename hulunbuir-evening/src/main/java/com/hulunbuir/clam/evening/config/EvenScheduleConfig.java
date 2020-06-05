package com.hulunbuir.clam.evening.config;

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
public class EvenScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(scheduleTaskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService scheduleTaskExecutor() {

//        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
//        threadPoolTaskScheduler.setPoolSize(20);
//        threadPoolTaskScheduler.setThreadNamePrefix("taskExecutor-");
//        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
//        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);

        return Executors.newScheduledThreadPool(5);
    }

}
