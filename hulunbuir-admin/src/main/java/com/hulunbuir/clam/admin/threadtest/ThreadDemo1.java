package com.hulunbuir.clam.admin.threadtest;

/**
 * <p>
 * explain:使用继承thread类，学习多线程
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/16 17:12
 */
public class ThreadDemo1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("当前执行Thread的run方法的数字是：-->" + i);
        }
    }

}
