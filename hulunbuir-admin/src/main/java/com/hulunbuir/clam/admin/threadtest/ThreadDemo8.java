package com.hulunbuir.clam.admin.threadtest;

/**
 * <p>
 * explain:使用实现runnable接口，学习多线程之礼让
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/16 17:19
 */
public class ThreadDemo8 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("线程睡眠出现异常!!!" + e);
            }
            System.out.println("当前ThreadDemo8<结束>执行Runnable的run方法的数字是：-->" + Thread.currentThread().getName() + "-->" + i);
        }
    }

}
