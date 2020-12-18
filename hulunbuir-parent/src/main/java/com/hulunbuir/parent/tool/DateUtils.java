package com.hulunbuir.parent.tool;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 * Explain:系统中的工具类进行处理日期
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 11:45
 */
public final class DateUtils {


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

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    private static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
        return simformat.format(date);
    }

    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date d = sdf.parse(date);
        return DateUtils.getDateFormat(d, format);
    }

}
