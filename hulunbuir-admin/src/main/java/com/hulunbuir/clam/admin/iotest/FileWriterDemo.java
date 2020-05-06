package com.hulunbuir.clam.admin.iotest;

import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>
 * explain: 将数据写入文件中
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/30 15:18
 */
public class FileWriterDemo {

    /**
     * 创建文件并进行处理流异常
     *
     * @author wangjunming
     * @since 2020/4/30 16:14
     */
    public void FileWriterDemoMethods001() {
        FileWriter fileWriter = null;
        try {
            fileWriter  = new FileWriter("FileWriterDemo.txt");
//        调用wirter方法将字符串写到流中
            fileWriter.write("你好，我是王俊明，很高兴认识你!!!");
//        刷新流对象中的缓冲的数据到文件
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("文件操作异常："+e);
        } finally {
            if(fileWriter != null){
                try {
//        关闭流资源，但是关闭之前会刷新一次内部的缓冲中的数据
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("关闭流异常！！！");
                }
            }
        }
    }

    /**
     * 对已有的文件进行续写
     *
     * @author wangjunming
     * @since 2020/4/30 16:17
     */
    public void FileWriterDemoMethods002() {
        FileWriter fileWriter = null;
        try {
            fileWriter  = new FileWriter("FileWriterDemo.txt",true);
//        调用wirter方法将字符串写到流中
            fileWriter.write("\r\n你好，我是王俊明，很高兴认识你!!!");
//        刷新流对象中的缓冲的数据到文件
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("文件操作异常："+e);
        } finally {
            if(fileWriter != null){
                try {
//        关闭流资源，但是关闭之前会刷新一次内部的缓冲中的数据
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("关闭流异常！！！");
                }
            }
        }
    }


}
