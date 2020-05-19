package com.hulunbuir.clam.admin.threadtest;

import java.util.concurrent.Callable;

/**
 * <p>
 * explain: 使用实现callable接口，学习多线程
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/16 17:40
 */
public class ThreadDemo4实现callable接口 implements Callable<String> {

    @Override
    public String call() throws Exception {
        String res = null;
        for (int i = 0; i < 100; i++) {
            res = Thread.currentThread().getName() + "---使用了callcable线程--" + i;
            System.out.println(res);
        }
        return res;
    }

}
