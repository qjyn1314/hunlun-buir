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

    /**
     * HashTable容器使用synchronized来保证线程安全，但在线程竞争激烈的情况下HashTable的效率非常低下。
     * 因为当一个线程访问HashTable的同步方法时，其他线程访问HashTable的同步方法时，可能会进入阻塞或轮询状态。
     * 如线程1使用put进行添加元素，线程2不但不能使用put方法添加元素，并且也不能使用get方法来获取元素，所以竞争越激烈效率越低。
     *
     * @author wangjunming
     * @since 2020/5/26 11:33
     */
    public static void main(String[] args) {
        Hashtable<String, Object> hashTable = new Hashtable<>();
        Integer hashTableNum = 100000;
        hashTable.put("qjyn1314", hashTableNum);
        System.out.println(hashTable.get("qjyn1314"));
    }


}
