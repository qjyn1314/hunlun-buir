package com.hulunbuir.clam.admin.test_demo;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-03-18 17:59
 */
public class ThreadDemo extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("ThreadDemo  --- run  " + i);
        }
    }
}
