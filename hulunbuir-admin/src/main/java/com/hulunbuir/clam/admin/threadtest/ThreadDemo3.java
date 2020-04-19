package com.hulunbuir.clam.admin.threadtest;

/**
 * <p>
 * explain:出现了并发的问题,即：当多个线程处理同一个变量的时候出现了并发问题
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/16 17:26
 */
public class ThreadDemo3 implements Runnable {
    boolean flag = true;
    private int ticketNum = 100;

    @Override
    public void run() {
        while (flag) {
            buyticket();
        }
    }

    private synchronized void buyticket() {
        if (ticketNum <= 0) {
            this.flag = false;
            return;
        }
        try {
            //主要是为了放大问题的出现性
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("线程睡眠出现异常!!!");
        }
        System.out.println(Thread.currentThread().getName() + "买了第" + ticketNum-- + "张票");
    }


}
