package com.hulunbuir.clam.admin.threadtest;

/**
 * <p>
 * explain:使用实现runnable接口，学习多线程之礼让
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/16 17:19
 */
public class ThreadDemo7 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("当前ThreadDemo7<结束>执行Runnable的run方法的数字是：-->" + Thread.currentThread().getName()+"-->"+i);
        }
    }

}
