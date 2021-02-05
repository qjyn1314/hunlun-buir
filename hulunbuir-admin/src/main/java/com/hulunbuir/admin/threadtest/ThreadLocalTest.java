package com.hulunbuir.admin.threadtest;

/**
 * <p>
 * explain:
 *
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/2 13:45
 */
public class ThreadLocalTest {

    public static ThreadLocal<String> threadLocal01 = new ThreadLocal<>();
    public static ThreadLocal<String> threadLocal02 = ThreadLocal.withInitial(() -> "HelloWorld");
    public static ThreadLocal<String> threadLocal03 = new InheritableThreadLocal<>();

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal01.get());
        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal02.get());
        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal03.get());
        threadLocal03.set("threadLocal03");
        new Thread(new MyThreadLocalRun()).start();
        new Thread(new MyThreadLocalRun()).start();
        new Thread(new MyThreadLocalRun()).start();


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

