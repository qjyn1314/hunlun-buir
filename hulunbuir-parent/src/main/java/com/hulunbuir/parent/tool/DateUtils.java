package com.hulunbuir.parent.tool;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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


    private final static String DATE_TIMES_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getFormatDate(Date date, String patent) {
        return DateFormatUtils.format(date, patent);
    }

    public static Date getNowDate() {
        return new Date();
    }

    public static String getDateTimes() {
        return getFormatDate(getNowDate(), DATE_TIMES_FORMAT);
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

    /**
     * 判断某一个时间点是否不在时间段内 某一个时间点  小于  开始时间 或 某一个时间点  大于  结束时间  返回true
     * @param nowTime 某一个时间点
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return true
     * Date1.after(Date2),当Date1大于Date2时，返回TRUE，当小于等于时，返回false；
     * Date1.before(Date2)，当Date1小于Date2时，返回TRUE，当大于等于时，返回false；
     * 如果业务数据存在相等的时候，而且相等时也需要做相应的业务判断或处理时，请注意。
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar nowDate = Calendar.getInstance();
        nowDate.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (nowDate.before(begin) || nowDate.after(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws ParseException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = "20210417";
        final Date nowDate= dateFormat.parse(nowTime);
        String beginTime = "20210416";
        final Date beginDate= dateFormat.parse(beginTime);
        String endTime = "20210418";
        final Date endDate = dateFormat.parse(endTime);
        boolean belongCalendar = belongCalendar(nowDate, beginDate, endDate);
        System.out.println(belongCalendar ? "根据预算使用时间" + dateTimeFormat.format(nowDate) + "，不再预算周期内(" + dateTimeFormat.format(beginDate) + "~" + dateTimeFormat.format(endDate) + ")，请添加新的预算信息。" : "通过");
    }

}
