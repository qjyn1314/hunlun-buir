package com.hulunbuir.clam.admin.threadtest;

/**
 * <p>
 * explain:使用实现runnable接口，学习多线程
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/16 17:19
 */
public class ThreadDemo2实现runnable接口 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("当前执行Runnable的run方法的数字是：-->" + i);
        }
    }

}
