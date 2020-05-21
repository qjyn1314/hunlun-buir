package com.hulunbuir.clam.admin.datastructure;

import java.util.Hashtable;

/**
 * <p>
 * explain: Hashtable与HashMap是差不多一样的，线程安全，是在方法上加了 synchronized 关键字，效率较低，一般采用 线程安全的ConcurrentHashMap
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/20 14:48
 */
public class HashTableDemo {


    public static void main(String[] args) {
        Hashtable<String, Object> hashTable = new Hashtable<>();
        Integer hashTableNum = 100000;
        hashTable.put("qjyn1314", hashTableNum);
        System.out.println(hashTable.get("qjyn1314"));
    }


}
