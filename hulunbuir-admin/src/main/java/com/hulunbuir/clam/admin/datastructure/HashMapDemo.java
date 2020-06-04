package com.hulunbuir.clam.admin.datastructure;

import java.util.HashMap;

/**
 * <p>
 * explain：学习hashmap的源码进行阅读并理解
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/18 21:18
 */
public class HashMapDemo {

    /**
     * study HashMap
     * <p>
     * 参考与： https://mp.weixin.qq.com/s?__biz=MzAwMjk5Mjk3Mw==&mid=2247488690&idx=4&sn=4bf6759ed3d065f29af33101c0925d96&chksm=9ac0aa90adb72386992b8d13b404484f4840e53ba94b5f2e5a06654bf18a80af522da065acf8&scene=126&sessionid=1587864595&key=f439ca045a9aade48db6bf55777ba4a12c49bf54c28013b552c9e00f026b7c5edd4731dc053255e50bc64e393192a719f40b7a509ff5dffc6c1293f8e2a41140193f1ab4b2d8c303d0e4df0dc92b5d00&ascene=1&uin=MjE1OTE0MjYzMA%3D%3D&devicetype=Windows+10+x64&version=6209005d&lang=zh_CN&exportkey=A5RLo8NfPXcOJHcyCXNffdA%3D&pass_ticket=afcLgabG0dzvk%2F%2Bki1Pnk85xOB6u2qVHjqTgdv6GJ%2B6cNIWZdcaU9LgHq46Zsv6O
     * https://blog.csdn.net/woshimaxiao1/article/details/83661464
     * HashMap 源码和底层原理在现在面试中是必问的。因此，我们非常有必要搞清楚它的底层实现和思想。
     * <p>
     * 基本概念：
     * HashMap 根据键的 hashCode 值存储数据，大多数情况下可以直接定位到它的值，因而具有很快的访问速度，但遍历顺序却是不确定的。
     * HashMap 最多只允许一条记录的键为 null，允许多条记录的值为 null。
     * HashMap 非线程安全，即任一时刻可以有多个线程同时写 HashMap，可能会导致数据的不一致。
     * 如果需要满足线程安全，可以用 Collections 的 synchronizedMap 方法使HashMap 具有线程安全的能力，或者使用 ConcurrentHashMap。
     * <p>
     * 存储结构：
     * 在JDK1.7及之前，是用数组加链表的方式存储的，HashMap 里面是一个数组，然后数组中每个元素是一个单向链表
     * 众所周知，当链表的长度特别长的时候，查询效率将直线下降，查询的时间复杂度为 O(n)。
     * 原理：查找的时候，根据 hash 值我们能够快速定位到数组的具体下标，但是之后的话，需要顺着链表一个个比较下去才能找到我们需要的，时间复杂度取决于链表的长度，为 O(n)。
     * 另一种说法是JDK1.7的hashmap是有问题的：
     * <p>
     * JDK1.8 把它设计为达到一个特定的阈值之后，就将链表转化为红黑树，即
     * 其转换红黑树的目的：在于增加查询速度
     * 由于红黑树，是一个自平衡的二叉搜索树，因此可以使查询的时间复杂度降为O(logn)。
     * 原理：当链表中的元素超过了 8 个以后，会将链表转换为红黑树，在这些位置进行查找的时候可以降低时间复杂度为 O(logN)。
     * <p>
     * 常用的变量：
     * 1. //默认的初始化容量为16，必须是2的n次幂
     * static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
     * <p>
     * 2. //最大容量，如果带有参数的任何一个构造函数隐式地指定了一个更大的值*时使用。*必须是2的幂<= 1<<30。
     * static final int MAXIMUM_CAPACITY = 1 << 30;
     * <p>
     * 3. //默认的加载因子0.75，乘以数组容量得到的值，用来表示元素个数达到多少时，需要扩容。
     * //为什么设置 0.75 这个值呢，简单来说就是时间和空间的权衡。
     * //若小于0.75如0.5，则数组长度达到一半大小就需要扩容，空间使用率大大降低，
     * //若大于0.75如0.8，则会增大hash冲突的概率，影响查询效率。
     * //加载因子存在的原因，还是因为减缓哈希冲突，如果初始桶为16，等到满16个元素才扩容，某些桶里可能就有不止一个元素了。
     * //.75，也就是说大小为16的HashMap，到了第13个元素，就会扩容成32。
     * static final float DEFAULT_LOAD_FACTOR = 0.75f;
     * <p>
     * 4. //刚才提到了当链表长度过长时，会有一个阈值，超过这个阈值8就会转化为红黑树
     * static final int TREEIFY_THRESHOLD = 8;
     * <p>
     * 5. //当红黑树上的元素个数，减少到6个时，就退化为链表
     * static final int UNTREEIFY_THRESHOLD = 6;
     * <p>
     * 6. //链表转化为红黑树，除了有阈值的限制，还有另外一个限制，需要数组容量至少达到64，才会树化。
     * //这是为了避免，数组扩容和树化阈值之间的冲突。
     * static final int MIN_TREEIFY_CAPACITY = 64;
     * <p>
     * 7. //存放所有Node节点的数组
     * transient Node<K,V>[] table;
     * <p>
     * 8.//存放所有的键值对
     * transient Set<Map.Entry<K,V>> entrySet;
     * <p>
     * 9. //map中的实际键值对个数，即数组中元素个数
     * transient int size;
     * <p>
     * 10. //每次结构改变时，都会自增，fail-fast机制，这是一种错误检测机制。
     * //当迭代集合的时候，如果结构发生改变，则会发生 fail-fast，抛出异常。
     * transient int modCount;
     * <p>
     * 11. //数组扩容阈值
     * int threshold;
     * <p>
     * 12. //加载因子
     * final float loadFactor;
     * <p>
     * 13. //普通单向链表节点类
     * static class Node<K,V> implements Map.Entry<K,V>
     * 其中 key的hash值，put和get的时候都需要用到它来确定元素在数组中的位置
     * <p>
     * 14. //转化为红黑树的节点类
     * static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V>
     *
     * @author wangjunming
     * @since 2020/5/19 14:05
     */
    public static void main(String[] args) {
//      创建hashmap的四种方式：

//      构造一个空的HashMap，具有默认初始容量(16)和默认负载因子(0.75)。
        HashMap<String, Object> map1 = new HashMap<>();

//      构造一个空的HashMap，具有指定的初始容量和缺省负载因子(0.75)。但是需要注意当前我们指定的容量并不一定就是实际的容量
        HashMap<String, Object> map2 = new HashMap<>(5);

//      构造一个具有指定初始容量和负载因子的HashMap。
//      这里就是把我们指定的容量改为一个大于它的的最小的2次幂值，如传过来的容量是14，则返回16
//      注意这里，按理说返回的值应该赋值给 capacity，即保证数组容量总是2的n次幂，为什么这里赋值给了 threshold 呢？
        HashMap<String, Object> map3 = new HashMap<>(10, 0.36f);

//      使用与指定的Map相同的映射构造一个新的HashMap。创建HashMap时使用缺省负载因子(0.75)和足以容纳指定Map中的映射的初始容量。
        HashMap<String, Object> map5 = new HashMap<>();
        HashMap<String, Object> map4 = new HashMap<>(map5);

        /*此方法是hashmap中的扩容机制，进行扩容时的此机制返回扩容后的容量*/
        final int tableSizeFor = tableSizeFor(4097);
        System.out.println(tableSizeFor);

        /*
         * JDK1.7的put方法详解：
         * put方法，会先调用一个hash()方法，得到当前key的一个hash值。
         * 用于确定key应该存放在数组的哪个下标位置(通过当前key的hash值取模(%)数组的长度 来确定key在数组的什么位置)，
         * 如果新的hash值与已有的hash值出现了碰撞，则使用头插法将此键值对添加至此数组下标的线性链表中，
         *
         * 之后则对value进行hash值比较，依次排列在链表中，当链表中的数据长度达到阈值8就会转化为红黑树。
         * 转化为红黑树目的：是为了增加效率，以及查询速度。
         *
         * 因为多线程环境下，使用Hashmap进行put操作会引起死循环，导致CPU利用率接近100%，所以在并发情况下不能使用HashMap。
         *
         * JDK1.8的put方法详解：
         *
         * HashMap由数组+链表组成的：
         * 数组是HashMap的主体，而链表则是主要为了解决哈希冲突(hash碰撞)而存在的。
         * 如果定位到的数组位置不含链表（当前entry的next指向null）,那么查找，添加等操作很快，仅需一次寻址即可；
         * 如果定位到的数组包含链表，对于添加操作，其时间复杂度为O(n)，首先遍历链表，存在即覆盖，否则新增；
         * 对于查找操作来讲，仍需遍历链表，然后通过key对象的equals方法逐一比对查找。
         * 所以，性能考虑，HashMap中的链表出现越少，性能才会越好。
         *
         * 线程不安全的hashmap出现的问题：
         * 添加元素方法 -> 添加新节点方法 -> 扩容方法 -> 把原数组元素重新分配到新数组中
         * put()  --> addEntry()  --> resize() -->  transfer()
         * 问题就发生在 transfer  这个方法中。
         *
         */
        String hashKey = "qjyn1314";
        map2.put(hashKey, tableSizeFor);
        System.out.println(hash(hashKey));
        System.out.println(Integer.toBinaryString(hash(hashKey)));
        System.out.println(map2.get("qjyn1314"));

    }

    public static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
    }

    /**
     * hash（散列、杂凑）函数，是将任意长度的数据映射到有限长度的域上。
     * 直观解释起来，就是对一串数据m进行杂糅，输出另一段固定长度的数据h，作为这段数据的特征（指纹）。
     * 也就是说，无论数据块m有多大，其输出值h为固定长度。
     * 到底是什么原理？将m分成固定长度（如128位），依次进行hash运算，然后用不同的方法迭代即可（如前一块的hash值与后一块的hash值进行异或）。
     * 如果不够128位怎么办？用0补全或者用1补全随意，算法中约定好就可以了。
     * <p>
     * 1.抗碰撞能力：对于任意两个不同的数据块，其hash值相同的可能性极小；对于一个给定的数据块，找到和它hash值相同的数据块极为困难。
     * 2.抗篡改能力：对于一个数据块，哪怕只改动其一个比特位，其hash值的改动也会非常大。
     * <p>
     * 对于方法中key：(key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
     * 若非空，则先计算key的hashCode值，赋值给h，然后把h右移16位，并与原来的h进行异或处理。
     * <p>
     * 为什么要这样做，这样做有什么好处呢？
     * 目的是让hash值的碰撞概率降到更低，好处是 位运算 速度比较快。
     * <p>
     * (h = key.hashCode()) ^ (h >>> 16)
     * 其实相当于，我们把高16位值和当前h的低16位进行了混合，这样可以尽量保留高16位的特征，从而降低哈希碰撞的概率。
     * <p>
     * 为什么会使用异或运算？而不使用其他的运算？
     * 两个值进行与运算，结果会趋向于0；或运算，结果会趋向于1；而只有异或运算，0和1的比例可以达到1:1的平衡状态。
     * 所以，异或运算之后，可以让结果的随机性更大，而随机性大了之后，哈希碰撞的概率当然就更小了。
     *
     * @author wangjunming
     * @since 2020/5/19 16:53
     */
    public static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
