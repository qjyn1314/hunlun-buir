package com.hulunbuir.admin.datastructure;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * explain:学习ConcurrentHashMap，面试的高频面点
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/20 14:49
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("qjyn1314", "qjyn1314@163.com");
    }

}
