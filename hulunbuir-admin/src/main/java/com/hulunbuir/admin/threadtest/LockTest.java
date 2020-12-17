package com.hulunbuir.admin.threadtest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/25 14:52
 */
public class LockTest {

    /**
     * 只要涉及到并发，一定会涉及锁
     *
     * 其中synchronized与lock(灵活度非常高)的区别是什么？
     * 1.synchronized  内置的java关键字，lock是一个java类
     * 2.synchronized  无法判断获取锁的状态，lock可以判断是否获取到了锁
     * 3.synchronized  会自动释放锁，lock必须手动的释放锁，如果不释放，则产生死锁
     * 4.synchronized  线程1（获取锁，阻塞），线程2（等待，傻傻的等）;lock锁就不一定会等待下去，使用lock.tryLock()进行尝试获取锁
     * 5.synchronized  课冲如梭，不可以中断的，非公平；lock可重入锁，可以判断锁，非公平，可以自己设置
     * 6.synchronized  适合锁少量的代码同步问题，lock适合所大量的同步代码
     *
     * @author wangjunming
     * @since 2020/8/25 15:03
     */
    public  static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            Integer num = 100/0;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }

    public static synchronized void mains(String[] args) {

    }

}
