package com.hulunbuir.clam.admin.threadtest;

import java.util.concurrent.ExecutionException;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/16 17:21
 */
public class ThreadTest {

    /**
     * 再次温习多线程：
     * https://www.bilibili.com/video/BV1V4411p7EF?p=12
     * https://www.bilibili.com/video/BV1B7411L7tE?from=search&seid=10632221365712270480
     * <p>
     * https://www.bilibili.com/video/BV1p4411P7V3?t=115
     *
     * <p>
     * 创建线程的两种方式：
     * 继承thread类：
     * <p>
     * 实现runnable接口：
     * <p>
     * 实现callable接口:
     * <p>
     * 其设计原理上，实现runnable接口使用了静态代理模式
     * <p>
     * lambad表达式：只有一行代码的情况下才能简化成一行代码，如果多行，那么就用代码块包裹。
     * 前提是接口为函数式接口，函数式接口是指，接口中只有一个方法。
     *
     * @author wangjunming
     * @since 2020/4/16 17:24
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
//        创建线程的第一种方式
        ThreadDemo1 threadDemo1 = new ThreadDemo1();
        threadDemo1.start();
//        创建线程的第二种方式
        new Thread(new ThreadDemo2()).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("当前执行main方法的数字是：-->" + i);
        }
//        创建线程的第三种方式
        ThreadDemo4 threadDemo1 = new ThreadDemo4();
        ThreadDemo4 threadDemo2 = new ThreadDemo4();
        ThreadDemo4 threadDemo3 = new ThreadDemo4();
        ThreadDemo4 threadDemo4 = new ThreadDemo4();
//        创建执行的服务
        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        提交执行的线程
        Future<String> submit = executorService.submit(threadDemo1);
        Future<String> submit1 = executorService.submit(threadDemo2);
        Future<String> submit2 = executorService.submit(threadDemo3);
        Future<String> submit3 = executorService.submit(threadDemo4);
//        获取执行的结果
        String submits = submit.get();
        String submit1s = submit1.get();
        String submit2s = submit2.get();
        String submit3s = submit3.get();
        System.out.println(submits);
        System.out.println(submit1s);
        System.out.println(submit2s);
        System.out.println(submit3s);
//        关闭服务
        executorService.shutdown();
        */

//      将线程进行外部干扰的停止
        /*ThreadDemo5 threadDemo5 = new ThreadDemo5();
        new Thread(threadDemo5).start();
        for (int i = 0; i < 1000; i++) {
            if (i == 100) {
                threadDemo5.stop();
                System.out.println("停止ThreadDemo5该线程!");
            }
            System.out.println("当前执行main方法的数字是：-->" + i);
        }*/

//        练习礼让-
//        ThreadDemo6 threadDemo6 = new ThreadDemo6();
//        new Thread(threadDemo6, "张三").start();
//        new Thread(threadDemo6, "李四").start();
//        new Thread(threadDemo6, "王五").start();
//        练习线程插队
//        ThreadDemo7 threadDemo7 = new ThreadDemo7();
//        Thread thread007 = new Thread(threadDemo7, "进行插队");
//        thread007.start();
//
//        for (int i = 0; i < 300; i++) {
//            if (i == 20) {
//                thread007.join();
//            }
//            System.out.println("运行的主方法，测试线程插队!!!--" + i);
//        }
//        练习并查看线程的状态
//        ThreadDemo8 threadDemo8 = new ThreadDemo8();
//        Thread thread008 = new Thread(threadDemo8);
//        Thread.State state = thread008.getState();
//        //刚创建出来
//        System.out.println(state);
//        //让运行起来
//        thread008.start();//启动线程
//        state = thread008.getState();
//        System.out.println(state);
//        //睡上1秒
//        while (state != Thread.State.TERMINATED){
//            Thread.sleep(100);
//            state = thread008.getState();
//            System.out.println(state);
//        }
//        System.out.println("线程停止了");



//        当多个线程处理同一个变量的时候出现了并发问题
        ThreadDemo3实现runnable接口测试锁 threadDemo3 = new ThreadDemo3实现runnable接口测试锁();
        new Thread(threadDemo3,"张三").start();
        new Thread(threadDemo3,"李四").start();
        new Thread(threadDemo3,"王五").start();





















    }

}
