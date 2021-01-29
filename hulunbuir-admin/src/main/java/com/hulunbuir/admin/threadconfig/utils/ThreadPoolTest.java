package com.hulunbuir.admin.threadconfig.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/11/25 13:28
 */
@Slf4j
public class ThreadPoolTest {


    public static void main(String[] args) {
        log.info("main方法开始");
        for (int i = 0; i < 200; i++) {
            ThreadPoolEntity threadPoolEntity = new ThreadPoolEntity("JACK"+i);
            ThreadPoolUtils.getTaskExecutor().execute(threadPoolEntity);
        }
        ThreadPoolUtils.getTaskExecutor().shutdown();
        final List<Runnable> runnables = ThreadPoolUtils.getTaskExecutor().shutdownNow();
        System.out.println(runnables);
        log.info("main方法结束");
    }


}
