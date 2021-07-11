package com.hulunbuir.admin.worktest;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/7/11 14:56
 */
public class Interview {


    {
        System.out.println("1");
    }

    static {
        System.out.println("2");
    }

    public static void main(String[] args) {
//        Interview interview1 = new Interview();
//        Interview interview2 = new Interview();
//        //输出什么？ 21    211
        submit(1);
        // b a
        // bbbbbbbb  exception
    }

    public static void submit(int i) {
        if (i == 2) {
            System.out.println("a");
        } else {
            System.out.println("b");
            submit(i++);
        }
    }

}
