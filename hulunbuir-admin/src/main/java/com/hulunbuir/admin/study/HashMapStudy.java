package com.hulunbuir.admin.study;

import java.util.HashMap;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/1/13 9:53
 */
public class HashMapStudy {

    /**
     * jdk8
     *
     * HashMap 里面是一个数组，然后数组中每个元素是一个单向链表。
     *
     * 默认数组长度是 16
     *
     * capacity：当前数组容量，始终保持 2^n，可以扩容，扩容后数组大小为当前的 2 倍。
     *
     * loadFactor：负载因子，默认为 0.75。
     *
     * threshold：扩容的阈值，等于 capacity * loadFactor   。
     *
     * TREEIFY_THRESHOLD ：转换为红黑树的阈(yu)值    8
     *
     * put
     *
     * 计算具体数组位置：
     * 1、首先会对当前key进行一个hash运算， (h = key.hashCode()) ^ (h >>> 16)  hash值的与高位的hash值进行  异或运  算，其目的是为了减少hash值的碰撞。即hash值一样会发生hash碰撞。
     * 2、对当前计算出的hash值与数组长度取模之后的值，就是在数组中的下标位置。
     * 如果发现当前key的hash值一样的情况下，则插入到链表的最后面，当链表的长度达到阈值8 的时候，则将链表转换为红黑树。
     * 如果当前的size的长度大于阈值时，进入resize()，扩容到原来数组长度的2倍，并进行数据迁移。
     *
     *get
     *
     * 1、计算 key 的 hash 值，根据 hash 值找到对应数组下标: hash & (length-1)。
     * 2、判断数组该位置处的元素是否刚好就是我们要找的，如果不是，走第三步。
     * 3、判断该元素类型是否是 TreeNode，如果是，用红黑树的方法取数据，如果不是，走第四步。
     * 4、遍历链表，直到找到相等(==或equals)的 key。
     *
     * @author wangjunming
     * @since 2021/1/13 15:17
     */
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();


    }

}