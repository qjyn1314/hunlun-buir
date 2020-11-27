package com.hulunbuir.clam.admin.threadconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * explain:线程池配置类：
 * 参考：
 * https://www.cnblogs.com/yw0219/p/8810956.html
 * https://www.cnblogs.com/zedosu/p/6665306.html
 * https://www.jianshu.com/p/7ab4ae9443b9
 *
 * </p>
 *
 * @author wangjunming
 * @since 2020/11/22 21:03
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    /**
     *
     * @author wangjunming
     * @since 2020/11/22 21:35
     */
    @Bean
    public TaskExecutor hulunExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(5);
        // 设置最大线程数
        executor.setMaxPoolSize(10);
        // 设置队列容量
        executor.setQueueCapacity(20);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称
        executor.setThreadNamePrefix("hulunBuir-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }


}