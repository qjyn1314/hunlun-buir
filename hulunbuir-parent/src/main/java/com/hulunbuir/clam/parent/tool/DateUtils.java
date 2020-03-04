package com.hulunbuir.clam.parent.tool;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * <p>
 * Explain:系统中的工具类进行处理日期
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 11:45
 */
public final class DateUtils extends DateUtil {


    private final static String dateTimesFormat = "yyyy-MM-dd HH:mm:ss";

    public static String getFormatDate(Date date, String patent) {
        return DateFormatUtils.format(date, patent);
    }

    public static Date getNowDate() {
        return new Date();
    }

    public static String getDateTimes() {
        return getFormatDate(getNowDate(), dateTimesFormat);
    }


}
