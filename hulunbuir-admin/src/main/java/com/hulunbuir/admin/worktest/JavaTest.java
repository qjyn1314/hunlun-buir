package com.hulunbuir.admin.worktest;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/16 14:28
 */
public class JavaTest {

    public static void main(String[] args) {
        //页码的数量
        int pageNo = 2;
        pageNo = (pageNo - 1) < 0 ? 0 : pageNo - 1;
        System.out.println(pageNo);
        int pageNum = 2;
        pageNum = Math.max((pageNum - 1), 0);
        System.out.println(pageNum);

    }

}
