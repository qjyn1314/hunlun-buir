package com.hulunbuir.clam.admin.iotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <p>
 * explain: 练习转换流
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/8 13:06
 */
public class InputStreamReaderDemo {

    public static void main(String[] args) throws IOException {
        final InputStream in = System.in;
        //转换流，即字节流通向字符流的桥梁，即将文件中的数据读取出来
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if ("over".equalsIgnoreCase(line)) {
                break;
            }
            System.out.println(line.toUpperCase());
        }
        bufferedReader.close();
        inputStreamReader.close();
    }

}
