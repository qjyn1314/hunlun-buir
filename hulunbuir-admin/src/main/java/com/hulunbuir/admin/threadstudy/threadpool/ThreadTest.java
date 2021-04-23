package com.hulunbuir.admin.threadstudy.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/11/22 21:17
 */
@Slf4j
@Component
public class ThreadTest {

    @Autowired
    private ThreadService service;
    @Autowired
    private ThreadPoolTaskExecutor hulunExecutor;

//    @Scheduled(cron = "0/10 * * * * ?")
    public void threadServiceSayHello() {
        log.info(">>>>> cron测试定时任务-每10秒执行一次检查线程池信息开始....");
        service.sayHello("ThreadService");
        log.info(">>>>> cron测试定时任务-每10秒执行一次检查线程池信息结束....");

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.shutdown();

    }


}
