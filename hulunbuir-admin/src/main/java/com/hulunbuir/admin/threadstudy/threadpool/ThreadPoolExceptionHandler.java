package com.hulunbuir.admin.threadstudy.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * explain:出现异常后的拒绝策略
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/28 10:28
 */
@Slf4j
public class ThreadPoolExceptionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.error(String.format("线程池出现异常！线程池大小:%d，%s", executor.getQueue().size(), r.toString()));
    }
}
