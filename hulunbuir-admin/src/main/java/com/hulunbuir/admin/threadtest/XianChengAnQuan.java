package com.hulunbuir.admin.threadtest;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/2 9:47
 */
public class XianChengAnQuan {
    private static volatile String name = "hello";

    /**
     * volatile 关键字：
     * volatile 关键字原理的前提知识：
     * 学习线程提前了解的基本概念：
     * 每一个线程都对应着一块工作空间，对主内存块中进行数据的处理。
     * 处理流程：
     * 将主内存中的数据拿到，进行更改，之后再写入到主内存中。
     * 被修饰的变量只是对各个线程保证了可见性，而并没有保证原子性。
     *
     */
    public static void main(String[] args) {

    }




}
