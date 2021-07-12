package com.hulunbuir.admin.threadstudy.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
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
    @Autowired(required = false)
    private ThreadPoolTaskExecutor applicationTaskExecutor;

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


    public static void main(String[] args) {
        log.info("main方法开始");
        for (int i = 0; i < 200; i++) {
            ThreadPoolEntity threadPoolEntity = new ThreadPoolEntity("JACK"+i);
            ThreadPoolConfigUtils.getTaskExecutor().execute(threadPoolEntity);
        }
        ThreadPoolConfigUtils.getTaskExecutor().shutdown();
        final List<Runnable> runnables = ThreadPoolConfigUtils.getTaskExecutor().shutdownNow();
        System.out.println(runnables);
        log.info("main方法结束");
    }



}
