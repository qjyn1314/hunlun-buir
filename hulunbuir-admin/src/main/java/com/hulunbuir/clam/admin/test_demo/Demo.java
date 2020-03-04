package com.hulunbuir.clam.admin.test_demo;

import java.util.Arrays;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-17 12:00
 */
public class Demo {
    private Integer ko;

    {
        Integer k = 1000;
        ko = k;
    }


    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        int c = a++;
        System.out.println(c);
        System.out.println(a);

        Demo demo = new Demo();
        Integer ko = demo.ko;
        System.out.println(ko);
        /*
        变量扥为 局部变量、成员变量、静态变量

        常量和final

        基本数据类型：
        byte<short<int<long<
        整数类型，默认是int
        float<double
        小数类型，默认是double
        char

        boolean

        BigDecimal
        自增：
        ++在变量之前，则现将变量增加1然后进行赋值
        ++在变量之后，则再次使用的时候将变量增加1然后进行赋值
        自减：
        -- 对应减少
        位移：
        左移乘2的倍数 ，右移除2的倍数

        三元运算：
        z ? x :y ;

        在逻辑运算中

        &  |  ~
        0对应true
        1对应false

        &&
        短路与

        ||
        短路或

        自动转化：
        byte i = 12;
        short k = i;
        int j = k;
        long h = j;
        float f = h;
        double d = f;

          */

        int jiecheng = jiecheng(10);
        System.out.println(jiecheng);


        int x = 0;

        while (x++ > 3) {
            System.out.println("while  循环" + x);
        }

        for (int j = 0; j < 3; j++) {
            System.out.println("for  循环" + j);
        }
    /*
    当腰对某些语句执行很多次的时候，就使用循环结构
    */
        System.out.println("当要对某些语句执行很多次的时候，就使用循环结构");
        for (int n = 5; n > 0; n--) {
            for (int m = 0; m < n; m++) {
                System.out.print("*");
            }
            System.out.println();
        }
        int[] xint = {3, 12, 14, 5, 69, 47, 85, 15, 26, 14, 45, 65,};
        for (int i = 0;i<xint.length; i++){
            System.out.println("xint["+i+"]"+xint[i]);
        }
        System.out.println(Arrays.toString(xint));





    }

    /**
     * 递归求阶乘
     *
     * @param i:
     * @return int
     * @author wangjunming
     * @since 2020/2/28 10:15
     */
    private static int jiecheng(int i) {
        if (i == 1) {
            return 1;
        } else {
            return i * jiecheng(i - 1);
        }
    }

}
