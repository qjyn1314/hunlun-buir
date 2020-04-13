package com.hulunbuir.clam.admin.study;

import com.alibaba.fastjson.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 说明：巩固java知识
 * </p >
 *
 * @author wangjunming
 * @since 2019-07-13 12:10
 */
public class JavaStudy {


    /**
     * 成员变量会进行初始化值
     * 局部变量则需要手动初始化值
     * ===========================================================================================================================================================
     * 什么是java序列化，如何实现java序列化?
     * 简要解释：
     * 　　序列化就是一种用来处理对象流的机制，所谓对象流也就是将对象的内容进行流化。可以对流化后的对象进行读写操作，也可将流化后的对象传输于网络之间。
     * 　　序列化是为了解决在对对象流进行读写操作时所引发的问题。
     * 序列化的实现：将需要被序列化的类实现Serializable接口，该接口没有需要实现的方法，implements Serializable只是为了标注该对象是可被序列化的，
     * 然后使用一个输出流(如：FileOutputStream)来构造一个ObjectOutputStream(对象流)对象，
     * 接着，使用ObjectOutputStream对象的writeObject(Object obj)方法就可以将参数为obj的对象写出(即保存其状态)，要恢复的话则用输入流。
     * 详细解释：
     * 当两个进程在进行远程通信时，彼此可以发送各种类型的数据。无论是何种类型的数据，都会以二进制序列的形式在网络上传送。
     * 发送方需要把这个Java对象转换为字节序列，才能在网络上传送；接收方则需要把字节序列再恢复为Java对象。
     * 　　只能将支持 java.io.Serializable 接口的对象写入流中。
     * 每个 serializable 对象的类都被编码，编码内容包括类名和类签名、对象的字段值和数组值，以及从初始对象中引用的其他所有对象的闭包。
     * 1.概念
     * 　　序列化：把Java对象转换为字节序列的过程。
     * 　　反序列化：把字节序列恢复为Java对象的过程。
     * 2.用途
     * 　　对象的序列化主要有两种用途：
     * 　　1） 把对象的字节序列永久地保存到硬盘上，通常存放在一个文件中；
     * 　　2） 在网络上传送对象的字节序列。
     * 3.对象序列化
     * 序列化API
     * 　　java.io.ObjectOutputStream代表对象输出流，它的writeObject(Object obj)方法可对参数指定的obj对象进行序列化，把得到的字节序列写到一个目标输出流中。
     * 只有实现了Serializable和Externalizable接口的类的对象才能被序列化。
     * 　　java.io.ObjectInputStream代表对象输入流，它的readObject()方法从一个源输入流中读取字节序列，再把它们反序列化为一个对象，并将其返回。
     * 4.说明
     * 　　读取对象的顺序与写入时的顺序要一致。
     * 　　对象的默认序列化机制写入的内容是：对象的类，类签名，以及非瞬态和非静态字段的值。
     * ===========================================================================================================================================================
     *
     * @param args
     */
    public static void main001(String[] args) {

        int[] arr = {23, 4545, 67, 222, 444, 3434, 90, 9999, 5555, 4444};
        System.out.println("排序之前：" + JSONArray.toJSONString(arr));
        bubbleSort(arr, arr.length);
        System.out.println("排序之后：" + JSONArray.toJSONString(arr));

    }


    /**
     * @description: 冒泡排序
     * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。
     * 如果不满足就让它俩互换。一次冒泡会让至少一个元素移动到它应该在的位置，重复n 次，
     * 就完成了 n 个数据的排序工作。
     * <p>
     * 原理：每次比较两个相邻的元素，将较大的元素交换至右端。
     * <p>
     * 思路：每次冒泡排序操作都会将相邻的两个元素进行比较，看是否满足大小关系要求，
     * 如果不满足，就交换这两个相邻元素的次序，一次冒泡至少让一个元素移动到它应该排列的位置，重复N次，就完成了冒泡排序。
     **/
    static void bubbleSort(int[] arr, int n) {
        //如果只有一个元素就不用排序了
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
            boolean flag = false;
            //此处你可能会疑问的j<n-i-1，因为冒泡是把每轮循环中较大的数飘到后面
            for (int j = 0; j < n - i - 1; ++j) {
                // 数组下标又是从0开始的，i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
                //  > -- 升序排序，< -- 降序排序
                if (arr[j] > arr[j + 1]) {
                    //即这两个相邻的数是逆序的，交换
                    //将大的数那个数保存
                    int temp = arr[j];
                    //将小的数交换到前面
                    arr[j] = arr[j + 1];
                    //将大的数交换到后面
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            System.out.println("第" + i + "次循环中交换之后的：" + JSONArray.toJSONString(arr));
            //没有数据交换，数组已经有序，退出排序
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 流的概念以及讲解：
     * <p>
     * 对于数据的输入输出操作以  流  方式进行，
     * 方向：输入输出
     * 单位：字节流字符流
     * 功能：节点流处理流
     *
     * MavenStudy m1 = new MavenStudy();
     * 1、因为new用到了MavenStudy.class文件所以会先找到class文件并加载到文件中
     * 2、执行该类的static代码块，如果有的话，给Person.class类进行初始化
     * 在对内存中尽力对象的特有属性并进行默认初始化
     * 对属性进行显示初始化
     * 对对象进行构造代码块进行初始化
     * 对对象进行对应的构造函数初始化
     * 将内存地址赋给栈内存中的m1变量
     *
     *
     *
     * ArrayList:数组结构
     * LinkedList:链表结构
     * HashSet:哈希表结构，线程非同步
     *    保证唯一性，通过元素的两个方法，hashCode和equals方法
     *    首先比较的元素的hashCode，然后比较的是equals方法
     *    自定义元素就是首先的重写hashCode（尽量保证hashCode不一样，有规律的hashCode性能，比如在年龄的属性上乘以27）和equals方法，
     *    判断是否存在，删除元素，也是比较的hashCode和equals方法
     * TreeSet:
     *   对填入对象必须实现comparable接口，具体实现compareto方法，然后对主要的条件，进行排序，然后次要，以此类推
     *   可以对元素进行排序，
     *   保证元素唯一性的依据是，compareto方法return 0
     *   二叉树结构
     *   排序的第一种方式是，让元素自身具备比较性，元素需要实现compareto方法
     *   这种方式也是自然排序
     *    当元素资深不具备比较性，或者比较性不是所需要的，这时需要让容器资深具备比较性，
     *    定义比较器，将比脚气对象作为参数传递给TreeSet集合，的构造参数
     *    当两种排序都存在时，以比较器为主
     *
     *    比较器的定义：定义一个类，实现Comparetor接口，覆盖compare方法
     *
     * 泛型：
     *      为了让java具备安全性，主要是转换异常，
     *      会在运行时将这种异常给开发人员呈现出来，开发人员进行处理
     *
     *     类上面的泛型，
     *     方法上面的泛型
     *     接口上的泛型
     *
     *     泛型的限定
     *     ？通配符
     *     ？ extends E ： 可以接收E类型或者E的子类型。上限
     *     ？ super E : 可以接收E类型或者E的父类型。 下限
     *
     *     泛型为类 接口的扩展使用
     *
     *  Map
     *   HashMap：哈希表结构，允许存入null键和null值，该集合是线程不同步的
     *   HashTable：哈希结构，不允许存入null键和null值，该集合是线程同步的
     *   TreeMap：底层是二叉树数据结构，线程不同步，可用于给map集合中的键进行排序
     *
     * Set底层就是使用了map集合。
     *
     *
     */
    public static void main(String[] args) throws Exception {


        List<String> demolists = new ArrayList<>(2);

        demolists.add("ni");
        demolists.add("niqw");

        System.out.println(demolists.toString());

        String remove = demolists.remove(0);

        System.out.println(remove);

        Object[] objects = demolists.toArray();

        System.out.println(objects[0]);

        Integer i = 9;
        Integer j = 8;
        int i2 = Integer.bitCount(i);
        System.out.println(i2);
        Integer i1 = i * j;

        System.out.println(i1);

    }

    private static void ioStudy() throws Exception {
        InputStream in = new FileInputStream(new File("D:\\javaio\\abc.txt"));
        OutputStream out = new FileOutputStream(new File("D:\\javaio\\abc"));
        int read = in.read();
        System.out.println(read);
        while (in.read() != -1) {
            out.write(in.read());
        }
        BufferedInputStream bin = new BufferedInputStream(in);
        int read1 = bin.read();
        while (read1 != -1) {
            System.out.println(read1);
        }
        if (in != null || out != null) {
            in.close();
            out.close();
        }
    }

}
