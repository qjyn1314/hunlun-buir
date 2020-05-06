package com.hulunbuir.clam.admin.iotest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <p>
 * explain:将文件中数据读取出来
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/30 16:33
 */
public class FileReaderDemo {

    /**
     * 读取文件
     *
     * @author wangjunming
     * @since 2020/4/30 16:37
     */
    public void FileReaderDemoMethods001() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("FileWriterDemo.txt");
            int read;
            char[] bytes = new char[4056];
            while ((read = fileReader.read(bytes)) != -1){
                System.out.print(new String(bytes,0,read));
            }
        } catch (FileNotFoundException e) {
            System.err.println("读取文件..文件找不到异常！！！" + e);
        } catch (IOException e) {
            System.err.println("读取文件异常！！！" + e);
        } finally {
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
