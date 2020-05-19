package com.hulunbuir.clam.admin.iotest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * explain:为学习javaIO流
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/30 15:17
 */
public class IoTest {

    /**
     * 其实io流是使用了
     * <p>
     * <p>
     * 装饰者模式：
     * 当想要对已有的对象进行功能增强时，
     * 可以定义类，将已有对象传入，基于已有的功能，并提供功能加强
     * 那么自定义类的该类成为装饰类
     * <p>
     * 其实就是BufferedWriter对FileWriter进行了增强
     * <p>
     * 采用 继承或者实现 进行装饰
     * 即：构造函数传递，功能增强
     * 装饰类通常会通过构造方法接收被装饰的对象。
     * 并基于被装饰的对象的功能，提供更强的功能。
     * <p>
     * 装饰模式比继承更要灵活，避免继承体系的臃肿，
     * 而且降低了类与类之间的关系
     * <p>
     * 装饰类因为增强已有对象，具备功能和已有的是相同的，只不过提供了更强功能
     * 所以装饰类和被装饰类通常是都属于一个体系中的。
     *
     * @author wangjunming
     * @since 2020/5/7 15:19
     */
    public static void main(String[] args) throws IOException {


/*
//字符流进行对文件中的数据进行增加和读取
//向文件中写数据
        FileWriterDemo fileWriterDemo = new FileWriterDemo();
//      fileWriterDemo.FileWriterDemoMethods001();
        fileWriterDemo.FileWriterDemoMethods002();
//读取文件中的数据
        FileReaderDemo fileReaderDemo = new FileReaderDemo();
        fileReaderDemo.FileReaderDemoMethods001();
//使用缓冲区向文件中写数据
        BufferedWriterDemo bufferedWriterDemo = new BufferedWriterDemo();
        bufferedWriterDemo.BufferedWriterDemoMethods001();
//使用缓冲区读取文件中的数据
        BufferedReaderDemo bufferedReaderDemo = new BufferedReaderDemo();
        bufferedReaderDemo.BufferedReaderDemoMethods001();
*/

        List<String> list = new ArrayList<>(10);
        list.add(0, "123asdasd");
        list.addAll(list);
        list.add(1, "zxczxczxcvzxcx");
        System.out.println();
        System.out.println("可以向list中进行添加数据，并且数据是会往后进行移动");
        System.out.println(list);

        String orderis = "qweqwe1312";

        String[] orderids = {"1","2","3"};

        System.out.println(Arrays.asList(orderis));
        System.out.println(Arrays.asList(orderids));



/*
//使用字节流对文件中的数据进行增加和读取

//使用字节流向文件中的数据进行写数据
        FileOutputStreamDemo outputStreamDemo = new FileOutputStreamDemo();
        outputStreamDemo.FileOutputStreamDemoMethods001();
//使用字节流读取文件中的数据
        FileInputStreamDemo inputStreamDemo = new FileInputStreamDemo();
        inputStreamDemo.FileInputStreamDemoMethods001();
*/


/*
流操作的基本规律：
最痛苦的就是流对象有很多，不知道该用哪一个

一般是通过两个明确来完成
1、
明确源和目的：
    源：输入流  InputStream  Reader
    目的：输出流  OutputStream  Writer

2、
明确操作的数据是否是纯文本：
    是：则使用字符流
    不是：则使用字节流

3、
当具体的对象确定之后：

*/

/*
        final Properties properties = System.getProperties();
        properties.list(System.out);
*/

        FileDemo fileDemo = new FileDemo();
        fileDemo.FileDemoMethods001();


    }

}
