package com.hulunbuir.admin.iotest;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>
 * explain:读取文件中的数据
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/7 16:26
 */
public class FileInputStreamDemo {

    public void FileInputStreamDemoMethods001() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("FileOutputStreamDemo.txt");
            byte[] bytes = new byte[1024];
            int read = -1;
            while ((read = inputStream.read(bytes)) != -1){
                System.out.println(new String(bytes,0,read));
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }


    }
}
