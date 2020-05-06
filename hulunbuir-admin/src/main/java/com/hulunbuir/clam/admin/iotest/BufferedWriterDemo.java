package com.hulunbuir.clam.admin.iotest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>
 * explain: 缓冲区读文件
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/2 18:46
 */
public class BufferedWriterDemo {

    public void BufferedWriterDemoMethods001() {
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter("BufferedWriterDemo.txt");
            bufferedWriter  = new BufferedWriter(fileWriter);
//        调用wirter方法将字符串写到流中
            bufferedWriter.write("你好，我是王俊明，很高兴认识你!!!");
//        刷新流对象中的缓冲的数据到文件
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("文件操作异常："+e);
        } finally {
            if(bufferedWriter != null){
                try {
//        关闭流资源，但是关闭之前会刷新一次内部的缓冲中的数据
                    bufferedWriter.close();
                } catch (IOException e) {
                    System.out.println("关闭流异常！！！");
                }
            }
        }
    }



}
