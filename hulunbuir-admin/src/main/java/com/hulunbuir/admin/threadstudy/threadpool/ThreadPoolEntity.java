package com.hulunbuir.admin.threadstudy.threadpool;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.util.Date;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/11/25 13:28
 */
@Slf4j
@ToString
public class ThreadPoolEntity implements Runnable {

    private String name;
    private String data;

    public ThreadPoolEntity() {
    }

    public ThreadPoolEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        log.info("{}-->执行的线程池实体类的时间是：{}", this.name, DateFormat.getDateInstance().format(new Date()));
    }
}
