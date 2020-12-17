package com.hulunbuir.admin.iotest;

import java.io.*;

/**
 * <p>
 * explain:转换流，
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/8 13:44
 */
public class OutputStreamWriterDemo {

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("F:\\IDEA_entertainment\\hunlun-buir\\hulunbuir-admin\\src\\main\\java\\com\\hulunbuir\\admin\\iotest\\OutputStreamWriterDemo.java"));

        final InputStream in = System.in;

        //转换流，即字节流通向字符流的桥梁
        // in  可以文件字节流（FileInputStream）也可以是键盘输入
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        final OutputStream out = System.out;
        //转换流，即字符流通向字节流的桥梁
        //out  可以是文件字节流(FileInputStream)，也可以是控制台
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if ("over".equalsIgnoreCase(line)) {
                break;
            }
            bufferedWriter.write(line.toUpperCase());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }

        bufferedReader.close();
        inputStreamReader.close();
        outputStreamWriter.close();
        bufferedWriter.close();

    }

}
