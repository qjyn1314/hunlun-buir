package com.hulunbuir.clam.common.utils;

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

}
