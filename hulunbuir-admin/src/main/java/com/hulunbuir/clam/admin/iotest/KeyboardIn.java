package com.hulunbuir.clam.admin.iotest;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * explain:学习键盘录入，
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/8 11:05
 */
public class KeyboardIn {


    public static void main(String[] args) throws IOException {

        InputStream inputStream = System.in;
        int keyboardIn = 0;
        while ((keyboardIn = inputStream.read()) != -1) {
            System.out.println(keyboardIn);
        }


    }


}
