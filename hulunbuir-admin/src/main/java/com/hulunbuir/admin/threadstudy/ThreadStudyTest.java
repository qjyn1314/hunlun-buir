package com.hulunbuir.admin.threadstudy;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static cn.hutool.core.thread.ThreadUtil.sleep;

/**
 * <p>
 * explain: 多线程，并行，并发，juc，线程池-->学习记录：
 * <p>
 * 进程：
 * 进程是用来加载指令、管理内存、管理IO的
 * <p>
 * 线程：
 * 一个进程内可以分为多个线程。
 * <p>
 * java中，线程为最小的调度单位，进程作为分配资源的最小单位。在windows中进程是不活动的，只是作为线程的容器
 * <p>
 * 并发：
 * 线程轮流使用cpu的做法称为并发，concurrent。
 * 即：同一时间应对多件事情的能力
 * <p>
 * 并行：
 * 在多核cpu中，每个核都可以调度运行线程，这时候线程可以是并行。
 * 即：同一时间动手做多件事情的能力
 * <p>
 * 原理之线程运行：
 * <p>
 * 1、栈与栈帧：
 * jvm是由堆、栈、方法区、组成。
 * <p>
 * 其中栈内存就是给线程用的，每一个线程启动后，虚拟机就会为其分配一块栈内存。
 * <p>
 * 每个栈是由多个栈帧组成，对应着每次方法调用时，所占的内存。
 * <p>
 * 每个线程只能有一个活动栈帧，对应着当前正在执行的那个方法。
 * <p>
 * synchronized 原理(只要涉及到并发，一定会涉及锁)：
 * <p>
 * synchronized 特性：
 * <p>
 * synchronized是可重入锁，内部锁对象中会有一个计数器记录线程获取几次锁啦，在执行完同步代码块时，计数器的数量会-1，直到计数器的数量为0，就释放这个锁。
 * <p>
 * 其中synchronized与lock(灵活度非常高)的区别是什么？
 * <p>
 * 1.synchronized  内置的java关键字，lock是一个java类
 * <p>
 * 2.synchronized  无法判断获取锁的状态，lock可以判断是否获取到了锁
 * <p>
 * 3.synchronized  会自动释放锁，lock必须手动的释放锁，如果不释放，则产生死锁
 * <p>
 * 4.synchronized  线程1（获取锁，阻塞），线程2（等待，傻傻的等）;lock锁就不一定会等待下去，使用lock.tryLock()进行尝试获取锁
 * <p>
 * 5.synchronized  课冲如梭，不可以中断的，非公平；lock可重入锁，可以判断锁，非公平，可以自己设置
 * <p>
 * 6.synchronized  适合锁少量的代码同步问题，lock适合所大量的同步代码
 * <p>
 * <p>
 * 创建线程的两种方式：
 * 继承thread类： ThreadStudyTest#test001()
 * <p>
 * 实现runnable接口：ThreadStudyTest#test002()
 * <p>
 * 实现callable接口: ThreadStudyTest#test003()
 * <p>
 * 其设计原理上，实现runnable接口使用了静态代理模式
 * <p>
 * lambad表达式：只有一行代码的情况下才能简化成一行代码，如果多行，那么就用代码块包裹。
 * <p>
 * 前提是接口为函数式接口，函数式接口是指，接口中只有一个方法。
 * <p>
 * volatile 关键字：
 * <p>
 * 处理流程：
 * <p>
 * 将主内存中的数据拿到，进行更改，之后再写入到主内存中。
 * <p>
 * 被修饰的变量只是对各个线程保证了可见性，而并没有保证原子性。
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
//        studyTest.test004();
        studyTest.test007();
        studyTest.test008();
        studyTest.test009();
        studyTest.test010();
        studyTest.test011();
        studyTest.test012();
        studyTest.test013();
        studyTest.test014();
        studyTest.test015();
//        studyTest.test016();
        studyTest.test017();
        studyTest.test019();
        studyTest.test020();
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
     * Thread-的常用方法、状态、以及作用
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
        Thread thread = new Thread();
        thread.join();
    }


    private static int test007 = 5;
    private static final Object test007Lock = new Object();

    /**
     * 测试：多个线程对同一个变量做读写操作
     * <p>
     * 结果：多个线程对同一个变量做读写操作的变量值最后为：-9991
     * <p>
     * 原因：多个线程之间的上下文切换导致，即其jvm的执行指令交错。
     * <p>
     * 解决：使用 synchronized 关键字修饰共享变量，并加入 对象锁。
     * <p>
     * synchronized (test007Lock){
     * test007++;
     * }
     * <p>
     * synchronized 在此处，实际上是用对象锁保证了临界区内代码的原子性，临界区的代码对外是不可分割的，不会被线程所打断
     * <p>
     * 为了加身对此的理解，可以思考一下问题：
     * <p>
     * 1、如果把   synchronized (test007Lock){ 放在 for循环的外面，如何理解？  原子性操作，锁住的目标过大。
     * <p>
     * 2、如果 t1,t2 添加 synchronized (test007Lock)  而 t3,t4 添加 synchronized (test008Lock)  会如何？如何理解？  对于同一个共享资源中需使用同一个对象锁。
     * <p>
     * 3、如果只给了 t1 添加 synchronized (test007Lock) 而 t2,t3,t4 没有添加锁会怎么样？如何理解？ 对于同一个共享资源中需使用同一个对象锁。
     *
     * @author wangjunming
     * @since 2021/4/22 15:17
     */
    public void test007() throws InterruptedException {
        log.info("多个线程对同一个变量做增/改操作。");
        Thread t1 = new Thread("test0071") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0071-所执行的任务内容。");
                    synchronized (test007Lock) {
                        test007++;
                    }
                    log.info("test0071-{}", test007);
                }
            }
        };
        Thread t2 = new Thread("test0072") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0072-所执行的任务内容。");
                    synchronized (test007Lock) {
                        test007--;
                    }
                    log.info("test0072-{}", test007);
                }
            }
        };
        Thread t3 = new Thread("test0073") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0073-所执行的任务内容。");
                    synchronized (test007Lock) {
                        test007++;
                    }
                    log.info("test0073-{}", test007);
                }
            }
        };
        Thread t4 = new Thread("test0074") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0074-所执行的任务内容。");
                    synchronized (test007Lock) {
                        test007--;
                    }
                    log.info("test0074-{}", test007);
                }
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        log.info("多个线程对同一个变量做增/改操作的变量值最后为：{}", test007);
    }


    private static int test008 = 5;

    private synchronized static void increment() {
        //临界区
        test008++;
    }

    private synchronized static void decrement() {
        //临界区
        test008--;
    }

    /**
     * 临界区：
     * <p>
     * 1、一个程序运行多个线程是没有问题的
     * <p>
     * 2、问题在多个线程访问共享资源
     * <p>
     * 1-多个线程读共享资源其实也没问题
     * <p>
     * 2-多个线程对共享资源读写操作时发生指令交错，就会出现问题
     * <p>
     * 3、一段代码内如果存在对共享资源的多线程读写操作，称这段代码块为“临界区”
     * <p>
     * 为了避免临界区的竟态条件发生，有多种手段达到这种目的。
     * <p>
     * 解决：
     * <p>
     * 阻塞式的解决方案： synchronized 、 lock
     * <p>
     * 非阻塞式的解决方案：原子变量
     * <p>
     * synchronized，即对象锁。
     * <p>
     * 基本原理：主要采用互斥的方式让同一时刻至多只有一个线程能持有【对象锁】，其他线程再想获取这个【对象锁】时就会阻塞住。
     * <p>
     * 这样就能保证拥有锁的线程可以安全的执行临界区内的代码，不用担心线程上下文切换。
     * <p>
     * java中的互斥和同步都可以采用 synchronized 关键字来完成，但是还是有区别的：
     * <p>
     * 1、互斥是保证临界区的竟态条件发生，同一时刻只能有一个线程执行临界区代码
     * <p>
     * 2、同步是由于线程执行的先后、顺序不同，需要一个线程等待其他线程运行到某个点
     *
     * @author wangjunming
     * @since 2021/4/22 15:17
     */
    public void test008() throws InterruptedException {
        log.info("测试静态代码块。");
        Thread t1 = new Thread("test0081") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0081-所执行的任务内容-increment。");
                    increment();
                }
            }
        };
        Thread t2 = new Thread("test0082") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0082-所执行的任务内容-increment。");
                    increment();
                }
            }
        };
        Thread t3 = new Thread("test0083") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0083-所执行的任务内容-decrement。");
                    decrement();
                }
            }
        };
        Thread t4 = new Thread("test0084") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0084-所执行的任务内容-decrement。");
                    decrement();
                }
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        //线程睡眠
        TimeUnit.SECONDS.sleep(1);
        log.info("多个线程对代码块中的一个变量做读写操作的变量值最后为：{}", test008);

    }

    /**
     * 测试对象中的同步代码块，使用 synchronized 修饰临界区，对象锁为当前对象
     * <p>
     * 总结:
     * <p>
     * 1、成员方法上加入 synchronized 关键字，此是锁住的对象为 this，例如：synchronized (this)  等同于   public synchronized void increment()
     * <p>
     * 2、静态方法上加入 synchronized 关键字，此时锁住的对象为 当前类.class，例如：synchronized (ThreadStudyTest.class)  等同于   private synchronized static void decrement()
     *
     * @author wangjunming
     * @since 2021/4/23 14:55
     */
    public void test009() throws InterruptedException {
        log.info("测试类中的同步代码块。");
        Test009 test009 = new Test009();
        Thread t1 = new Thread("test0091") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0091-所执行的任务内容-increment。");
                    test009.increment();
                }
            }
        };
        Thread t2 = new Thread("test0092") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0092-所执行的任务内容-increment。");
                    test009.increment();
                }
            }
        };
        Thread t3 = new Thread("test0093") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0093-所执行的任务内容-decrement。");
                    test009.decrement();
                }
            }
        };
        Thread t4 = new Thread("test0094") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    log.info("test0094-所执行的任务内容-decrement。");
                    test009.decrement();
                }
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        //线程睡眠
        TimeUnit.SECONDS.sleep(1);
        log.info("多个线程对代码块中的一个变量做读写操作的变量值最后为：{}", test009.get());
    }


    static class Test009 {

        private int test009 = 10;

        public void increment() {
            synchronized (this) {
                //临界区
                test009++;
            }
        }

        public void decrement() {
            synchronized (this) {
                //临界区
                test009--;
            }
        }

        private int get() {
            synchronized (this) {
                return test009;
            }
        }

    }

    /**
     * 线程八锁，本质上是多线程访问共享资源的八种情况，加深对线程执行顺序的理解。
     *
     * @author wangjunming
     * @since 2021/4/23 15:24
     */
    public void test010() throws InterruptedException {
        log.info("线程八锁，本质上是多线程访问共享资源的八种情况，了解其执行顺序及结果。");
        log.info("第一种情况..run中调用的成员方法都被 synchronized 修饰，锁对象是 【成员方法的调用者】 线程之间是互斥的..start");
        Test010 test0101 = new Test010();
        Thread t1 = new Thread("test0101") {
            @Override
            public void run() {
                log.info("running...");
                test0101.a();
            }
        };
        Thread t2 = new Thread("test0102") {
            @Override
            public void run() {
                log.info("running...");
                test0101.b();
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info("第一种情况...end");
        log.info("第二种情况..run中调用的成员方法都被 synchronized 修饰，锁对象是 【成员方法的调用者】 ，线程之间是互斥的(a方法睡眠了一秒)..start");
        Test010 test0102 = new Test010();
        Thread t3 = new Thread("test0103") {
            @SneakyThrows
            @Override
            public void run() {
                log.info("test0103-所执行的任务内容-a。");
                sleep(1000);
                test0102.a();
            }
        };
        Thread t4 = new Thread("test0104") {
            @Override
            public void run() {
                log.info("test0104-所执行的任务内容-b。");
                test0102.b();
            }
        };
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        log.info("第二种情况...end");
        log.info("第三种情况..run中调用的方法没有全部被 synchronized所修饰 ，三个线程之间的锁没有互斥 ..start");
        Test010 test0103 = new Test010();
        Thread t5 = new Thread("test0105") {
            @SneakyThrows
            @Override
            public void run() {
                log.info("test0105-所执行的任务内容-a。");
                sleep(1000);
                test0103.a();
            }
        };
        Thread t6 = new Thread("test0106") {
            @Override
            public void run() {
                log.info("test0106-所执行的任务内容-b。");
                test0103.b();
            }
        };
        Thread t7 = new Thread("test0107") {
            @Override
            public void run() {
                log.info("test0107-所执行的任务内容-c。");
                test0103.c();
            }
        };
        t5.start();
        t6.start();
        t7.start();
        t5.join();
        t6.join();
        t7.join();
        log.info("第三种情况...end");
        log.info("第四种情况..两个独立的线程之间的锁没有关联关系..start");
        Test010 test0104 = new Test010();
        Test010 test0105 = new Test010();
        Thread t8 = new Thread("test0108") {
            @SneakyThrows
            @Override
            public void run() {
                log.info("test0108-所执行的任务内容-a。");
                sleep(1000);
                test0104.a();
            }
        };
        Thread t9 = new Thread("test0109") {
            @Override
            public void run() {
                log.info("test0109-所执行的任务内容-b。");
                test0105.b();
            }
        };
        t8.start();
        t9.start();
        t8.join();
        t9.join();
        log.info("第四种情况...end");
        log.info("第五种情况..被 static synchronized 修饰的方法(锁对象是 类.class 对象 )  与 synchronized(锁对象是 调用方法的对象 是 this )  修饰的方法 其锁对象不一致。..start");
        Test010 test0107 = new Test010();
        Thread t10 = new Thread("test01010") {
            @SneakyThrows
            @Override
            public void run() {
                log.info("test01010-所执行的任务内容-d。");
                sleep(1000);
                Test010.d();
            }
        };
        Thread t11 = new Thread("test01011") {
            @Override
            public void run() {
                log.info("test01011-所执行的任务内容-a。");
                test0107.a();
            }
        };
        t10.start();
        t11.start();
        t10.join();
        t11.join();
        log.info("第五种情况...end");
        log.info("第六种情况.线程都被 static synchronized 修饰的方法(锁对象是 类.class 对象 ) 线程之间是互斥的  ..start");
        Thread t12 = new Thread("test01012") {
            @SneakyThrows
            @Override
            public void run() {
                log.info("test01012-所执行的任务内容-e。");
                sleep(1000);
                Test010.e();
            }
        };
        Thread t13 = new Thread("test01013") {
            @Override
            public void run() {
                log.info("test01013-所执行的任务内容-f。");
                Test010.f();
            }
        };
        t12.start();
        t13.start();
        t12.join();
        t13.join();
        log.info("第六种情况...end");
        log.info("第七种情况...end");
        log.info("此情况与第五种情况的结果相同。");
        log.info("第七种情况...end");
        log.info("第八种情况...end");
        log.info("此情况与第六种情况的结果相同。");
        log.info("第八种情况...end");
        //线程睡眠
        TimeUnit.SECONDS.sleep(1);
        log.info("main线程结束......");
    }

    static class Test010 {
        public synchronized void a() {
            log.info("a");
        }

        public synchronized void b() {
            log.info("b");
        }

        public void c() {
            log.info("c");
        }

        public static synchronized void d() {
            log.info("d");
        }

        public static synchronized void e() {
            log.info("e");
        }

        public static synchronized void f() {
            log.info("f");
        }

    }

    /**
     * 成员变量和静态变量是否线程安全?
     * <p>
     * 如果没有被共享，则线程安全
     * <p>
     * 如果被共享了，根据他们的状态是否能够改变，分两种情况
     * <p>
     * 如果只有读操作，则线程安全
     * <p>
     * 如果有读写操作，则这块代码块是临界区，需要考虑线程安全
     * <p>
     * 局部变量是否是线程安全？
     * <p>
     * 局部变量是线程安全的
     * <p>
     * 但局部变量引用的对象则未必是线程安全的
     * <p>
     * 如果该对象没有逃离方法的作用方法，他是线程安全的
     * <p>
     * 如果该对象逃离方法的作用范围，就需要考虑线程安全
     * <p>
     * //方法可能会被子类去继承，这个时候可能会发生线程安全的问题
     *
     * @author wangjunming
     * @since 2021/4/23 16:40
     */
    public void test011() {

    }

    /**
     * synchronized 关键字：
     * <p>
     * Monitor(存储在java对象头)：被翻译成监视器或管程
     * <p>
     * 每一个java对象都可以关联一个Monitor对象，如果使用了 synchronized 给对象上了锁，之后，该对象头 markword中就会设置指向Monitor对象的指针。
     * <p>
     * <p>
     * synchronized  修饰在成员方法上，锁对象为当前调用的对象，即，this
     * <p>
     * synchronized  修饰在静态方法上，锁对象为class对象，即，类名.class
     * <p>
     * synchronized 的原理：
     * <p>
     * 线程安全的类
     * <p>
     * String
     * Integer
     * StringBuffer
     * Random
     * Vector
     * HashTable
     * java.util.concurrent 包下的类
     * <p>
     * 需要注意的是：
     * 其中每一个方法是线程安全的，但是方法的组合是线程不安全的
     *
     * @author wangjunming
     * @since 2021/4/23 17:04
     */
    public void test012() {
        log.info("String类中：其中每一个方法是线程安全的，但是方法的组合是线程不安全的。");
    }

    /**
     * 售票并发问题
     * <p>
     * 测试：模拟多个人来买票
     * <p>
     * 即，多个线程来买票
     * <p>
     * 解决:
     * 在买票方法上加上 synchronized 关键字
     *
     * @author wangjunming
     * @since 2021/4/25 11:12
     */
    public void test013() throws InterruptedException {
        TicketWindows windows = new TicketWindows(1000);
        List<Integer> sellCountList = new Vector<>();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                int sell = windows.sell(getRandomInt());
                try {
                    Thread.sleep(getRandomInt());
                } catch (InterruptedException e) {
                    log.error("睡眠失败，", e);
                }
                sellCountList.add(sell);
            }, "test013" + i);
            threadList.add(thread);
            thread.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        log.info("余票数为：{}", windows.getCount());
        log.info("已卖票数为：{}", sellCountList.stream().mapToInt(i -> i).sum());
    }

    public static Random random = new Random();

    public static int getRandomInt() {
        return random.nextInt(5) + 1;
    }

    /**
     * 售票窗口
     */
    static class TicketWindows {
        private Integer count;

        public TicketWindows(Integer count) {
            this.count = count;
        }

        public Integer getCount() {
            return count;
        }

        public synchronized int sell(Integer sellCount) {
            if (this.count >= sellCount) {
                this.count -= sellCount;
                return sellCount;
            } else {
                return 0;
            }
        }

    }

    /**
     * 两个人转账的并发问题
     * <p>
     * 解决：  synchronized (Account.class)  使用class对象作为锁对象。
     * <p>
     * 使用 synchronized 修饰 synchronized (Account.class)  使用类对象 作为锁对象。
     *
     * @author wangjunming
     * @since 2021/4/25 14:11
     */
    public void test014() throws InterruptedException {
        Account zhangsan = new Account(1000);
        Account lisi = new Account(1000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 2000; i++) {
                zhangsan.transfer(lisi, getRandomInt());
            }
        }, "test0141");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 2000; i++) {
                lisi.transfer(zhangsan, getRandomInt());
            }
        }, "test0142");
        t1.start();
        t2.start();
        t1.join();//父线程需要等待子线程执行完成后再执行
        t2.join();
        log.info("totalAmount：{}", (zhangsan.getAmount() + lisi.getAmount()));
    }

    static class Account {
        Integer amount;

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public Account(Integer amount) {
            this.amount = amount;
        }

        public void transfer(Account target, Integer transferAmount) {
            synchronized (Account.class) {
                if (this.amount >= transferAmount) {
                    this.setAmount(this.getAmount() - transferAmount);
                    target.setAmount(target.getAmount() + transferAmount);
                }
            }
        }

    }

    /**
     * ReentrantLock，
     * 特点：
     * 可重入；可中断；可以设置超时时间；可以设置为公平锁；支持多个条件变量；
     * <p>
     * 与 synchronized 一样的可以支持重入
     * <p>
     * 此为：练习可重入
     *
     * @author wangjunming
     * @since 2021/4/28 14:00
     */
    public void test015() {
        locktest015.lock();
        try {
            log.info("test015...");
            m1();
        } finally {
            locktest015.unlock();
        }

    }

    public void m1() {
        locktest015.lock();
        try {
            log.info("m1...");
            m2();
        } finally {
            locktest015.unlock();
        }
    }

    public void m2() {
        locktest015.lock();
        try {
            log.info("m2...");
        } finally {
            locktest015.unlock();
        }
    }

    public static ReentrantLock locktest015 = new ReentrantLock();

    /**
     * ReentrantLock，
     * 特点：
     * 可重入；可中断；可以设置超时时间；可以设置为公平锁；支持多个条件变量；
     * <p>
     * 此为：练习可中断
     *
     * @author wangjunming
     * @since 2021/5/17 10:31
     */
    public void test016() {
        Thread t1 = new Thread(() -> {
            log.info("尝试获取到锁");
            try {
                //如果没有竞争关系那么此方法就会获取lock 对象锁
                //如果有竞争关系就进入祖册队列，可以被其他线程用  interrupt 方法打断
                locktest016.lockInterruptibly();
            } catch (InterruptedException e) {
                log.error("打断锁失败，", e);
                log.info("没有获取到锁");
                return;
            }
            try {
                log.info("获取到锁");
            } finally {
                locktest016.unlock();
            }

        }, "test016");


        //lock 加锁
//        try {
        locktest016.lock();
//        } catch (Exception e) {
//            log.error("e",e);
//        }finally {
//            locktest016.unlock();
//        }

        //启动线程
        t1.start();

        //让主线程睡眠 1  秒
        sleep(1000);

        log.info("尝试打断--t1 ");
        //打断t1线程
        t1.interrupt();

    }

    public static ReentrantLock locktest016 = new ReentrantLock();

    /**
     * ReentrantLock，
     * 特点：
     * 可重入；可中断；可以设置超时时间；可以设置为公平锁；支持多个条件变量；
     * <p>
     * 此为：练习尝试获得锁
     * <p>
     * 解决死锁的问题：首先将尝试获得A锁，在获取 成功之后在尝试获取 B锁，如果B锁获取失败，则释放A锁
     *
     * @author wangjunming
     * @since 2021/5/17 10:31
     */
    public void test017() {
        log.info("test017----");
        Thread t1 = new Thread(() -> {
            log.info("尝试获取到锁");
            try {
                /*locktest017.tryLock();//主要是为了解决无限期的等待下去
                locktest017.tryLock(2,TimeUnit.SECONDS);*/
                if (!locktest017.tryLock(2, TimeUnit.SECONDS)) {
                    log.info("获取不到锁。");
                    return;
                }
            } catch (Exception e) {
                log.info("获取不到锁。", e);
                return;
            }
            try {
                log.info("获得到锁，此处为临界区的代码。");
            } finally {
                locktest017.unlock();
            }
        }, "test017");

        //1.在主线程获得到锁
        locktest017.lock();
        log.info("获得到锁");
        t1.start();
        //2、睡眠一秒后主线程释放锁，进而让其他线程获得到锁
        sleep(1000);
        log.info("释放锁");
        locktest017.unlock();

    }

    public static ReentrantLock locktest017 = new ReentrantLock();

    /**
     * ReentrantLock，
     * 特点：
     * 可重入；可中断；可以设置超时时间；可以设置为公平锁；支持多个条件变量；
     * <p>
     * 此为：练习条件变量，-- newCondition();
     * <p>
     * await 前需要获得锁
     * <p>
     * await 执行后，会释放锁，进入 ConditionObject 中等待
     * <p>
     * await 的线程被唤醒（或打断，或超时），将重新竞争lock锁
     * <p>
     * 在竞争 lock锁成功之后，从await后继续执行
     *
     * @author wangjunming
     * @since 2021/5/24 12:02
     */
    public void test018() throws InterruptedException {
        //创建一个新的 条件变量(休息室)
        Condition condition1 = locktest018.newCondition();
        //创建一个新的 条件变量(就餐厅)
        Condition condition2 = locktest018.newCondition();

        //获得锁
        locktest018.lock();

        //进入休息室
        condition1.await();

        //唤醒休息室
        condition1.signal();

        //唤醒所有的 条件变量
        condition1.signalAll();

    }

    public static ReentrantLock locktest018 = new ReentrantLock();

    /**
     * ReentrantLock，
     * 特点：
     * 可重入；可中断；可以设置超时时间；可以设置为公平锁；支持多个条件变量；
     * <p>
     * 此为：练习条件变量，-- newCondition();，并操作实例
     * <p>
     * await 前需要获得锁
     * <p>
     * await 执行后，会释放锁，进入 ConditionObject 中等待
     * <p>
     * await 的线程被唤醒（或打断，或超时），将重新竞争lock锁
     * <p>
     * 在竞争 lock锁成功之后，从await后继续执行
     *
     * @author wangjunming
     * @since 2021/5/24 12:02
     */
    public void test019() throws InterruptedException {
        log.info("test019----");
        Thread t1 = new Thread(() -> {
            log.info("尝试获取到饭锁");
            locktest019.lock();
            try {
                log.info("有饭没有：{}", condition1Flag);
                while (!condition1Flag) {
                    log.info("没饭，先歇会儿。");
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("拿到饭了，开始吃饭。");
                log.info("即，获得到锁，此处为临界区的代码。");
            } finally {
                locktest019.unlock();
            }
        }, "小南");

        Thread t2 = new Thread(() -> {
            log.info("尝试获取到烟锁");
            locktest019.lock();
            try {
                log.info("有烟没有：{}", condition2Flag);
                while (!condition2Flag) {
                    log.info("没烟，先歇会儿。");
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("拿到烟了，开始吃饭。");
                log.info("即，获得到锁，此处为临界区的代码。");
            } finally {
                locktest019.unlock();
            }
        }, "小明");

        t1.start();
        t2.start();

        Thread t3 = new Thread(() -> {
            locktest019.lock();
            try {
                condition1Flag = true;
                condition1.signal();
            } finally {
                locktest019.unlock();
            }
        }, "送饭的");
        t3.start();
        sleep(1000);
        Thread t4 = new Thread(() -> {
            locktest019.lock();
            try {
                condition2Flag = true;
                condition2.signal();
            } finally {
                locktest019.unlock();
            }
        }, "送烟的");
        t4.start();

    }

    public static ReentrantLock locktest019 = new ReentrantLock();

    /**
     * 创建一个新的 条件变量(吃饭-休息室)
     */
    public static Condition condition1 = locktest019.newCondition();
    /**
     * 创建一个新的 条件变量(抽烟-休息室)
     */
    public static Condition condition2 = locktest019.newCondition();

    public static boolean condition1Flag = false;
    public static boolean condition2Flag = false;

    /**
     * 在学习juc相关的关键字时的前提知识：
     * 共享内存模型--
     * java 内存模型-JMM：(java  memory model)
     * --它定义了 主存、工作内存 抽象概念 。
     * ---其底层对应着  cpu寄存器、缓存、硬件内存、cpu指令优化
     *
     * 主存：所有线程都共享的数据。
     * 工作内存：每个线程私有的数据，局部变量
     *
     *
     *
     * 主要体现在几方面：
     *
     * 可见性： 保证指令在不受到线程上下文切换的影响
     *
     * 原子性：保证指令不会受到cpu缓存的影响
     *
     * 有序性：保证指令不会受到cpu指令并行优化的影响
     *
     *
     * @author wangjunming
     * @since 2021/5/24 18:27
     */
    public void test020() {
        //实践线程的可见性问题
        Thread t20 = new Thread(()->{
            while (flag) {
                log.info("t20-线程");
                System.out.println(flag);
            }
        });
        t20.start();
        //在此处睡眠一秒，
        sleep(1000);
        log.debug("进行停止  t20-线程");
        //如果此变量的值生效则  t20线程则会停止。
        flag = false;
/*
        //t20线程一直在执行并没有在预想中结束运行。
        //为什么会出现此现象：
        // 首先t20线程会将主内存中读取出来flag的值,为 true，进行判断。
        // 然后JIT编译器会将flag的值缓存到t20线程的工作内存中，减少对主内存的访问。
        // 在main主线程改变主内存flag的值时， t20 线程还是从自己的工作内存中进行读取flag的值，flag的值还是  true，结果导致了t20线程一直在执行，并没有停下来。
        // 此实践的结论是 -- 因线程之间变量的可见性的问题。
解决可见性问题的方式：
//1、使用  volatile  易变关键字
    -- 它可以用来修饰成员变量，可以避免线程从自己工作缓存中查找变量的值，必须从主内存中找到变量并获取它的值，线程操作 volatile 关键字修饰的变量都是直接操作主存的
//2、使用 synchronized 关键字
    -- synchronized语句块，既可以保证代码块的原子性，也同时保证代码块内变量的可见性。但缺点是synchronized关键字是重量级操作，性能相对更低。

 */
        //实践线程的可见性问题的解决方式 使用  volatile  易变关键字
        Thread t3 = new Thread(() -> {
            while (volatileFlag) {
                log.info("t3-线程");
            }
        });
        t3.start();
        //在此处睡眠一秒，
        sleep(1000);
        log.debug("进行停止  t3-线程");
        //如果此变量的值生效则  t3 线程则会停止。
        volatileFlag = false;

        log.debug("开始测试 synchronized 创建 t4 线程");
        //实践线程的原子性问题的解决方式 使用  synchronized 关键字，重点-需要临界区的锁是同一把锁
        Thread t4 = new Thread(() -> {
            while (objFlag) {
                synchronized (obj) {
                    if (!objFlag) {
                        log.info("t4-线程执行中");
                        break;
                    }
                }
            }
        });
        t4.start();
        //在此处睡眠一秒，
        sleep(10000);
        log.debug("进行停止  t4-线程");
        //如果此变量的值生效则  t4 线程则会停止。
        synchronized (obj) {
            objFlag = false;
        }
        log.debug("可见性和原子性解决方式运行结束。");
        log.debug("......开始测试java指令的有序性。");
/*
    有序性：
    举例说明：
    指令重排序：
        java针对每一条指令都可以分为五个阶段： 取指令-指令译码-执行指令-内存访问-数据写回
        支持同时执行上述五个阶段的处理器，就可以称之为五级指令流水线。
        这时cpu可以在一个时钟周期内，同时运行五条指令不同阶段（相当于一条执行时间最长的复杂指令），IPC=1，本质上，流水线技术并不能缩短单条指令的执行时间，但是可以变相的提高指令的吞吐率。

    有序性 的解决方式，使用  volatile  关键字防止指令重排的问题。
*/
/*
volatile 关键字原理，解决了   可见性  有序性
：--
volatile 的底层实现原理是内存屏障，Memory Barrier
对 volatile 变量的写指令后会加入写屏障

对 volatile 变量的读指令前会加入读屏障

1、如何保证可见性
写屏障：保证在该屏障之前的，对共享变量的改动，都同步到主内存中

读屏障：保证该屏障之后，对共享变量的读取，加载的是主内存中的最新数据

2、如何保证有序性

写屏障：写屏障会确保指令重排时，不会将写屏障之前的代码排在写屏障之后

读屏障：读屏障会确保指令重排时，不会将读屏障之后的代码排在读屏障之前

注意：
写屏障仅仅是保证之后的读能够读到最新的结果，但不能保证读跑到它前面去
而有序性的保证也仅仅只是保证了本线程内相关代码不被重排序

 */







    }

    public static boolean flag = true;
    public static volatile boolean volatileFlag = true;
    public final static Object obj = new Object();
    public static boolean objFlag = true;

    /**
     * ThreadLocal学习
     */
    public static ThreadLocal<String> threadLocal01 = new ThreadLocal<>();
    public static ThreadLocal<String> threadLocal02 = ThreadLocal.withInitial(() -> "HelloWorld");
    public static ThreadLocal<String> threadLocal03 = new InheritableThreadLocal<>();

    /**
     * 对ThreadLocal的创建有几种方式
     *
     * @author wangjunming
     * @since 2021/4/26 15:50
     */
    public void test006() {
        log.info(Thread.currentThread().getName() + "-->" + threadLocal01.get());
        log.info(Thread.currentThread().getName() + "-->" + threadLocal02.get());
        log.info(Thread.currentThread().getName() + "-->" + threadLocal03.get());
        threadLocal03.set("threadLocal03");
        new Thread(new ThreadStudyTest.MyThreadLocalRun()).start();
        new Thread(new ThreadStudyTest.MyThreadLocalRun()).start();
        new Thread(new ThreadStudyTest.MyThreadLocalRun()).start();
        threadLocal01.remove();
        threadLocal02.remove();
        threadLocal03.remove();
    }

    static class MyThreadLocalRun implements Runnable {
        public MyThreadLocalRun() {
            log.info(Thread.currentThread().getName() + "-->" + threadLocal01.get());
            log.info(Thread.currentThread().getName() + "-->" + threadLocal02.get());
            log.info(Thread.currentThread().getName() + "-->" + threadLocal03.get());
        }

        @Override
        public void run() {
            log.info(Thread.currentThread().getName() + "-->" + threadLocal01.get());
            log.info(Thread.currentThread().getName() + "-->" + threadLocal02.get());
            log.info(Thread.currentThread().getName() + "-->" + threadLocal03.get());
        }
    }


}
