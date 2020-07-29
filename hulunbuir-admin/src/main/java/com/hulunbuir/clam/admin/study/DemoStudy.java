package com.hulunbuir.clam.admin.study;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/6 18:42
 */
public class DemoStudy {


    public static void main(String[] args) throws Exception {
        String regx = "^\\d+(\\.\\d+)?,\\d+(\\.\\d+)?";
        String tel = "654.3215,1984.321";
        System.out.println(Pattern.compile(regx).matcher(tel).matches());

        String regx1 = "^\\d+";
        String tel2 = "15321355715";
        System.out.println(Pattern.compile(regx1).matcher(tel2).matches());


        String title = "123";
        final String[] split = StringUtils.split(title, "**", 2);
        System.out.println(Arrays.asList(split));


        String urlStr = "%E6%9C%BA%E6%9E%84%E4%BF%A1%E6%81%AF%E5%AF%BC%E5%85%A5%E6%A8%A1%E6%9D%BF.xlsx";
        String fileName = URLDecoder.decode(urlStr, "UTF-8");
        System.out.println(fileName);





    }











    public static void main20200728(String[] args) {
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

    public static void mains(String[] args) {
        String datetime1="20200702100000";
        String datetime2="20200628093000";
        final Date date1 = new Date(Long.parseLong(datetime1));
        final Date date2 = new Date(Long.parseLong(datetime2));
        final long newDateTime = date1.getTime() - date2.getTime();
        final int num = 60 * 60 * 24 * 1000;
        //相差的天数
        final long day = newDateTime / num;
        final Date date = new Date(day);
        final String yyyyMMddkkmmss = new SimpleDateFormat("yyyyMMddkkmmss").format(date);
        System.out.println(yyyyMMddkkmmss);
        String workday="20200702100000";
        final Date workdaydate = new Date(Long.parseLong(workday));
        final Calendar instance = Calendar.getInstance();
        instance.setTime(workdaydate);
        final int week = instance.get(Calendar.DAY_OF_WEEK);
        Integer[] weeks = {2,3,4,5,6};
        final boolean contains = Arrays.asList(weeks).contains(week);
        System.out.println("时间："+workdaydate+"是否是工作日："+(contains ? "是": "否"));
        //284. 44. 和15.4
        BigDecimal max = new BigDecimal("284.44");
        BigDecimal min = new BigDecimal("15.4");
        //相乘
        BigDecimal multiply = max.multiply(min);
        //相除
        final BigDecimal divide = max.divide(min).setScale(10, RoundingMode.UP);
        //相减
        BigDecimal subtract = multiply.subtract(divide);
        System.out.println(subtract);
        BigDecimal money =new BigDecimal(132900947);
        final DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
        final String format = decimalFormat.format(money);
        System.out.println(format);
    }
}
