package com.hulunbuir.clam.admin.study;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/6 18:42
 */
public class DemoStudy {

    public static void main(String[] args) {
        int[] arr = {14, 2, 36, 10, 1, 14, 2, 36, 10, 1};
        final long start = System.currentTimeMillis();
        final int max = getMax(arr, 0);
        final long end = System.currentTimeMillis();
        System.out.println(max);
        System.out.println(start - end);
    }

    /**
     * 获取最大值
     *
     * @param arr 数组
     * @param max 最大值
     * @return int
     */
    public static int getMax(int[] arr, int max) {
        if (null == arr) {
            return 0;
        }
        for (int i = 0; i < arr.length - 1; i++){
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

}
