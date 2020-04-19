package com.hulunbuir.clam.admin.threadtest;

/**
 * <p>
 * explain:使用实现runnable接口，学习多线程
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/16 17:19
 */
public class ThreadDemo5 implements Runnable {

    private boolean flag = false;

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            if (flag) {
                break;
            }
            System.out.println("当前执行Runnable的run方法的数字是：-->" + i);
        }
    }

    public void stop() {
        this.flag = true;
    }

}
