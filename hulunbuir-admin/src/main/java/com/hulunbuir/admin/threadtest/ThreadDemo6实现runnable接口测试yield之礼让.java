package com.hulunbuir.admin.threadtest;

/**
 * <p>
 * explain:使用实现runnable接口，学习多线程之礼让
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/16 17:19
 */
public class ThreadDemo6实现runnable接口测试yield之礼让 implements Runnable {

    @Override
    public void run() {
        System.out.println("当前ThreadDemo6<开始>执行Runnable的run方法的数字是：-->" + Thread.currentThread().getName());
        Thread.yield();
        System.out.println("当前ThreadDemo6<结束>执行Runnable的run方法的数字是：-->" + Thread.currentThread().getName());
    }

}
