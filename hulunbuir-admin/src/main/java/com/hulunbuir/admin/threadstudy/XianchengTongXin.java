package com.hulunbuir.admin.threadstudy;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain:生产者与消费者的练习与测试
 * 举例为演员表演，观众观看
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/25 15:19
 */
@Slf4j
public class XianchengTongXin {

    /**
     * 采用信号灯形式，根据标识让多线程之间进行通信，只针对两个线程而言的线程通信
     */
    public static void main(String[] args) {
        Tv tv = new Tv();
        new Player(tv).start();
        new Watcher(tv).start();
    }

}

/**生产者 演员*/
class Player extends Thread {

    Tv tv;

    public Player(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.play("开始对二取模了。");
            } else {
                this.tv.play("吃饭。");
            }
        }
    }
}

/**消费者 观众*/
class Watcher extends Thread {
    Tv tv;

    public Watcher(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.tv.watch();
        }
    }
}

/**同一个资源是电视*/
@Slf4j
class Tv {
    /**演员所说的话*/
    String notice;
    /**信号灯的标志*/
    boolean flag = true;

    /**演员表演节目*/
    public synchronized void play(String notice) {
        //演员等待
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                log.error("InterruptedException:",e);
            }
        }
        //开始表演
        System.out.println("表演了" + notice);
        this.notice = notice;
        //唤醒演员
        this.notifyAll();
        //切换标志
        this.flag = !this.flag;
    }

    /**观众观看节目*/
    public synchronized void watch() {
        //观众等待
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {

            }
        }
        //开始观看了
        System.out.println("听到了" + notice);
        //唤醒观众
        this.notifyAll();
        //切换标志
        this.flag = !this.flag;
    }

}








































