package com.hulunbuir.clam.admin.iotest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <p>
 * explain:使用缓冲区读取文件中的数据
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/30 16:33
 */
public class BufferedReaderDemo {

    /**
     * 读取文件
     *
     * @author wangjunming
     * @since 2020/4/30 16:37
     */
    public void BufferedReaderDemoMethods001() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("FileWriterDemo.txt");
            bufferedReader = new BufferedReader(fileReader);
            int read;
            char[] bytes = new char[4056];
            while ((read = bufferedReader.read(bytes)) != -1){
                System.out.print(new String(bytes,0,read));
            }
        } catch (FileNotFoundException e) {
            System.err.println("读取文件..文件找不到异常！！！" + e);
        } catch (IOException e) {
            System.err.println("读取文件异常！！！" + e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.err.println("处理异常失败！！！");
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    System.err.println("处理异常失败！！！");
                }
            }
        }
    }


}
