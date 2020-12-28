package com.hulunbuir.admin.threadconfig.utils;

import lombok.extern.slf4j.Slf4j;

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
        log.info("main方法结束");
    }


}
