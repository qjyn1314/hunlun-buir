package com.hulunbuir.parent.tool;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-29 23:17
 */
@Slf4j
public class CommonUtils {

    /**
     * 下划线转驼峰
     *
     * @author wangjunming
     * @since 2020/7/14 17:13
     */
    public static String underscoreToCamel(String value) {
        StringBuilder result = new StringBuilder();
        String[] arr = value.split("_");
        for (String s : arr) {
            result.append((String.valueOf(s.charAt(0))).toUpperCase()).append(s.substring(1));
        }
        return result.toString();
    }

    /**
     * 将单词的首字母大写
     *
     * @author wangjunming
     * @since 2020/7/17 13:59
     */
    public static String upperFirstLatter(String letter){
        return letter.substring(0, 1).toUpperCase()+letter.substring(1);
    }

    /**
     * 将单词的首字母大写
     *
     * @author wangjunming
     * @since 2020/7/17 13:59
     */
    public static String lowerFirstLatter(String letter){
        return letter.substring(0, 1).toLowerCase()+letter.substring(1);
    }



}
