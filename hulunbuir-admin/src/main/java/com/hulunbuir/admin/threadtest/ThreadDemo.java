package com.hulunbuir.admin.threadtest;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * explain: 学习juc包，了解并使用线程
 * </p>
 *
 * @author wangjunming
 * @since 2020/6/1 16:23
 */
@Slf4j
public class ThreadDemo {

    /**
     * 概念：
     * 并发编程：
     * <p>
     * 线程的状态： Thread.State.values()
     * <p>
     * sleep：
     *
     * @author wangjunming
     * @since 2020/6/1 16:23
     */
    public static void main(String[] args) throws InterruptedException {
        final Thread.State[] values = Thread.State.values();
        log.info("线程的状态：{}", Arrays.asList(values));
        //线程睡眠
        TimeUnit.SECONDS.sleep(1);

        final int i = 10 >>> 16;
        final int j = 11 >>> 16;
        System.out.println(i);
        System.out.println(j);


    }


}
