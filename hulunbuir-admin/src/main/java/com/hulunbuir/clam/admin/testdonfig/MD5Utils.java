package com.hulunbuir.clam.admin.testdonfig;

import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.hulunbuir.clam.parent.tool.DateUtils;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-03-02 14:53
 */
public class MD5Utils {

    public static void main(String[] args) {
        String dateTimes = DateUtils.getDateTimes();
        System.out.println(dateTimes);
        String s = SecureUtil.md5(dateTimes).toUpperCase();
        System.out.println(s);

    }


}
