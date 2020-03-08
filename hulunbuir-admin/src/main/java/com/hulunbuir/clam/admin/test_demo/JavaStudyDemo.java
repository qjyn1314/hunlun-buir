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
public class JavaStudyDemo {
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
        JavaStudyDemo demo = new JavaStudyDemo();
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
        for (int i = 0; i < xint.length; i++) {
            System.out.println("xint[" + i + "]" + xint[i]);
        }
        System.out.println(Arrays.toString(xint));
        int intValue = getIntValue(xint);
        System.out.println("最大的数是：");
        System.out.println(intValue);
        System.out.println("长度：");
        System.out.println(xint.length);
        sortMaoPao(xint);
        System.out.println(Arrays.toString(xint));
        int i = halerSelect(xint, 52);
        System.out.println(i);




    }

    //折半查找：数组已经是从大到小排好序
    private static int halerSelect(int[] arr, int key) {
        int min = 0, max = arr.length, mid = 0;
        //循环条件的判断，当最小的角标值小于等于最大的角标值的时候就不能进行循环的查找了
        while (min <= max) {
            mid = (min + max) / 2;
            if (key > arr[mid]) {
                max = mid - 1;
            } else if (key < arr[mid]) {
                min = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /*排序中最快的是希尔排序，还有快速排序，插入排序，其中冒泡排序和选择排序是最常见的*/

    /**
     * 冒泡排序
     * 将元素与相邻元素相比之后再置换，第一次出现的最值在角标的最后一位
     *
     * @param arr:
     * @return void
     * @author wangjunming
     * @since 2020/3/6 18:22
     */
    private static void sortMaoPao(int[] arr) {
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - j - 1; i++) {
                if (arr[i] < arr[i + 1]) {
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 将元素与每一个元素进行比较之后再置换位置，第一次最值出现在角标的第一位
     *
     * @param arr:
     * @return void
     * @author wangjunming
     * @since 2020/3/6 17:53
     */
    private static void sortSelect(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //每次拿着外层循环的值，与内层循环的每一个值进行循环比较
            //保证程序不做多余的比较，将j的值初始化为i+1，
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    /**
     * 获取最值
     *
     * @param arr:
     * @return int
     * @author wangjunming
     * @since 2020/3/6 17:42
     */
    private static int getIntValue(int[] arr) {
        int value = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (value < arr[i]) {
                value = arr[i];
            }
        }
        return value;
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
