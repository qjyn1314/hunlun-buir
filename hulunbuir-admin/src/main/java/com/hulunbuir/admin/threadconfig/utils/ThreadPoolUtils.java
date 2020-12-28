package com.hulunbuir.admin.threadconfig.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/28 10:21
 */
@Component
public class ThreadPoolUtils {
    /**
     * 核心线程数
     */
    private static final int CORE_SIZE = 2;
    /**
     * 最大线程数
     */
    private static final int MAX_SIZE = 500;
    /**
     * 线程活跃时间（秒）
     */
    private static final int KEEP_ALIVE_SECONDS = 5;
    /**
     * 任务队列数
     */
    private static final int QUEUE_CAPACITY = 100;

    /**
     * 获取自定义线程池
     *
     * @author wangjunming
     * @since 2020/12/28 10:34
     */
    public static ThreadPoolExecutor getTaskExecutor() {
        return ThreadPoolHolder.INSTANCE;
    }

    /**
     * 初始化自定义线程池
     *
     * @author wangjunming
     * @since 2020/12/28 10:35
     */
    private static class ThreadPoolHolder {
        public static final ThreadPoolExecutor INSTANCE;

        static {
            //ThreadPoolExceptionHandler表示当线程池处理不了规定任务时的异常处理方式，即拒绝策略。
            INSTANCE = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(QUEUE_CAPACITY), new ThreadPoolExceptionHandler());
        }
    }


}
