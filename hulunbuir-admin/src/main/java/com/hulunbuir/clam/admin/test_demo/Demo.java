package com.hulunbuir.clam.admin.test_demo;

import java.lang.reflect.Array;

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





























    }

    /**
     * 递归求阶乘
     *
     * @author wangjunming
     * @since 2020/2/28 10:15
     * @param i:
     * @return int
     */
    private static int jiecheng(int i) {
        if(i==1){
            return 1;
        }else{
            return i * jiecheng(i-1);
        }
    }

}
