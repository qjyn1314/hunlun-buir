package com.hulunbuir.admin.iotest;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>
 * explain: 向文件中进行写数据
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/7 16:26
 */
public class FileOutputStreamDemo {

    /**
     * 对已有数据的文件中进行续写数据，
     * 其本质就是在构造方法中增加传参，设置append 为 true
     *
     * @author wangjunming
     * @since 2020/5/7 17:06
     */
    public void FileOutputStreamDemoMethods001() {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("FileOutputStreamDemo.txt", true);
            outputStream.write("你好，我是王俊明！！！欢迎来到javaIO！".getBytes());
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }


    }


}
