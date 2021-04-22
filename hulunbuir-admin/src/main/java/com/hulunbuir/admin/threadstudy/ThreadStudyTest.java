package com.hulunbuir.admin.threadstudy;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * explain: 多线程，并行，并发，juc，线程池-->学习记录：
 * 进程：
 * 进程是用来加载指令、管理内存、管理IO的
 * 线程：
 * 一个进程内可以分为多个线程。
 * java中，线程为最小的调度单位，进程作为分配资源的最小单位。在windows中进程是不活动的，只是作为线程的容器
 * 并发：
 * 线程轮流使用cpu的做法称为并发，concurrent
 * 即：同一时间应对多件事情的能力
 * 并行：
 * 在多核cpu中，每个核都可以调度运行线程，这时候线程可以是并行
 * 即：同一时间动手做多件事情的能力
 * 原理之线程运行：
 * 1、栈与栈帧：
 * jvm是由堆、栈、方法区、组成。
 * 其中栈内存就是给线程用的，每一个线程启动后，虚拟机就会为其分配一块内存。
 * 每个栈是由多个栈帧组成，对应着每次方法调用时，所占的内存。
 * 每个线程只能有一个活动栈帧，对应着当前正在执行的那个方法。
 *
 *
 *
 *
 *
 *
 *
 * </p>
 * <p>
 * 只要涉及到并发，一定会涉及锁
 * <p>
 * 其中synchronized与lock(灵活度非常高)的区别是什么？
 * 1.synchronized  内置的java关键字，lock是一个java类
 * 2.synchronized  无法判断获取锁的状态，lock可以判断是否获取到了锁
 * 3.synchronized  会自动释放锁，lock必须手动的释放锁，如果不释放，则产生死锁
 * 4.synchronized  线程1（获取锁，阻塞），线程2（等待，傻傻的等）;lock锁就不一定会等待下去，使用lock.tryLock()进行尝试获取锁
 * 5.synchronized  课冲如梭，不可以中断的，非公平；lock可重入锁，可以判断锁，非公平，可以自己设置
 * 6.synchronized  适合锁少量的代码同步问题，lock适合所大量的同步代码
 * <p>
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
 * volatile 关键字：
 * volatile 关键字原理的前提知识：
 * 学习线程提前了解的基本概念：
 * 每一个线程都对应着一块工作空间，对主内存块中进行数据的处理。
 * 处理流程：
 * 将主内存中的数据拿到，进行更改，之后再写入到主内存中。
 * 被修饰的变量只是对各个线程保证了可见性，而并没有保证原子性。
 *
 *
 * @author wangjunming
 * @since 2021/3/30 11:40
 */
@Slf4j(topic = "c.ThreadStudyTest")
public class ThreadStudyTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadStudyTest studyTest = new ThreadStudyTest();
        studyTest.test001();
        studyTest.test002();
        log.info("main-主线程....");
        studyTest.test003();
        studyTest.test004();
    }

    /**
     * 使用thread类创建线程
     *
     * @author wangjunming
     * @since 2021/4/22 15:17
     */
    public void test001() {
        Thread t1 = new Thread("test001") {
            @Override
            public void run() {
                log.info("使用thread类创建线程-所执行的任务内容。");
            }
        };
        t1.start();
    }

    /**
     * 使用runable接口创建线程
     *
     * @author wangjunming
     * @since 2021/4/22 15:17
     */
    public void test002() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                log.info("使用runable接口创建线程-所执行的任务内容。");
            }
        };
        Thread t1 = new Thread(r1, "test002");
        t1.start();
    }


    /**
     * 使用callable接口创建线程
     *
     * @author wangjunming
     * @since 2021/4/22 15:17
     */
    public void test003() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("使用callable接口创建线程-所执行的任务内容并返回3000。");
                return 3000;
            }
        });
        Thread t3 = new Thread(futureTask, "test003");
        t3.start();
        //才此处会等待(阻塞)，直至得到返回结果
        Integer integer = futureTask.get();
        log.info("使用callable接口创建线程-其中的返回值是：{}", integer);
    }


    /**
     * 多线程同时执行，测试线程之间的交替执行
     *
     * @author wangjunming
     * @since 2021/4/22 15:17
     */
    public void test004() {
        log.info("测试线程之间的交替执行。");
        Thread t1 = new Thread("test0041") {
            @Override
            public void run() {
                while (true) {
                    log.info("test0041-所执行的任务内容。");
                }
            }
        };
        t1.start();
        Thread t2 = new Thread("test0042") {
            @Override
            public void run() {
                while (true) {
                    log.info("test0042-所执行的任务内容。");
                }
            }
        };
        t2.start();
    }


    /**
     * Thread-的常用方法、状态、
     *
     * @author wangjunming
     * @since 2021/4/22 15:17
     */
    public static void test005() throws InterruptedException {
        log.info(" Thread-的常用方法。");
        final Thread.State[] values = Thread.State.values();
        log.info("线程的状态：{}", Arrays.asList(values));
        //线程睡眠
        TimeUnit.SECONDS.sleep(1);
        //主要是为了放大问题的出现性
        Thread.sleep(100);
        Thread.yield();
        Thread.interrupted();
    }

    /**ThreadLocal学习*/
    public static ThreadLocal<String> threadLocal01 = new ThreadLocal<>();
    public static ThreadLocal<String> threadLocal02 = ThreadLocal.withInitial(() -> "HelloWorld");
    public static ThreadLocal<String> threadLocal03 = new InheritableThreadLocal<>();

    public void test006() {
        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal01.get());
        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal02.get());
        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal03.get());
        threadLocal03.set("threadLocal03");
        new Thread(new ThreadStudyTest.MyThreadLocalRun()).start();
        new Thread(new ThreadStudyTest.MyThreadLocalRun()).start();
        new Thread(new ThreadStudyTest.MyThreadLocalRun()).start();
    }

    static class MyThreadLocalRun implements Runnable {
        public MyThreadLocalRun(){
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal01.get());
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal02.get());
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal03.get());
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal01.get());
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal02.get());
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal03.get());
        }
    }


}
