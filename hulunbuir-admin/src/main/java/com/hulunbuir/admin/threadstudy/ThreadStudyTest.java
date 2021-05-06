package com.hulunbuir.admin.threadstudy;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

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
 *
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
     * <p>
     * <p>
     * <p>
     * <p>
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
     *
     * 可中断；可以设置超时时间；可以设置为公平锁；支持多个条件变量；
     *
     * 与 synchronized 一样的可以支持重入
     *
     * @author wangjunming
     * @since 2021/4/28 14:00
     */
    private void test015() {
        ReentrantLock lock = new ReentrantLock();


    }

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
