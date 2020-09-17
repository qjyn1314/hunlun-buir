package com.hulunbuir.clam.admin.leetcode;

import java.util.*;

/**
 * <p>
 * explain:两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/9 19:10
 */
public class SumTwoNumbers001 {

    public static void main(String[] args) {
        int[] numes = {2, 9, 1, 8, 12};
        int target = 9;
        final int[] ints = twoSum(numes, target);
        System.out.println(Arrays.toString(ints));
        List<String> list = new ArrayList<>();
        list.add("AA");
        list.add("BB");
        list.add("CC");
        final int[] dub = twoSums(numes, target);
        System.out.println(Arrays.toString(dub));
    }

    public static int[] twoSums(int[] nums, int target) {
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; ++i) {
            m.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; ++i) {
            int t = target - nums[i];
            if (m.containsKey(t) && m.get(t) != i) {
                res[0] = i;
                res[1] = m.get(t);
                break;
            }
        }
        return res;
    }

    /**
     * 减少了一次判断
     *
     * @author wangjunming
     * @since 2020/7/9 20:16
     */
    public static int[] dub(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                map.put(nums[0], 0);
            } else {
                int q = target - nums[i];
                if (map.containsKey(q)) {
                    res[0] = map.get(q);
                    res[1] = i;
                    return res;
                }
                map.put(nums[i], i);
            }
        }
        return res;
    }

    /**
     *
     *
     * @author wangjunming
     * @since 2020/7/9 20:14
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] arr;
        if (null == nums || nums.length < 1 || target < 0) {
            return new int[]{};
        }
        arr = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int t = target - nums[i];
            if (map.containsKey(t)) {
                arr[0] = i;
                arr[1] = map.get(t);
                break;
            }
        }
        return arr;
    }



    boolean isValid(String str) {
        Stack<Character> parentheses = null;
        final char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{') parentheses.push(chars[i]);
            else {
                assert parentheses != null;
                if (parentheses.empty()) return false;
                if (chars[i] == ')' && parentheses.peek() != '(') return false;
                if (chars[i] == ']' && parentheses.peek() != '[') return false;
                if (chars[i] == '}' && parentheses.peek() != '{') return false;
                parentheses.pop();
            }
        }
        assert parentheses != null;
        return parentheses.empty();
    }





}
