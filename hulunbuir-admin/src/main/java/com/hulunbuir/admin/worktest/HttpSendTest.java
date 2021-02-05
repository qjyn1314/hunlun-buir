package com.hulunbuir.admin.worktest;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/1/20 10:52
 */
@Slf4j
public class HttpSendTest {

    public static void main(String[] args) {
        System.out.println("abc".matches("..."));
        final Calendar instance = Calendar.getInstance();
        Integer budgetYear = 20210201;

        System.out.println(budgetYear.toString().substring(0,4));

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            final Date parse = simpleDateFormat.parse(budgetYear.toString());
            System.out.println(simpleDateFormat.format(parse));
        } catch (ParseException e) {
            log.info("");
        }
        final String byBudgetYear = getBudgetTypeByBudgetYear(budgetYear);
        System.out.println(byBudgetYear);
    }

    private static String getBudgetTypeByBudgetYear(Integer budgetYear) {
        final String budgetYearStr = budgetYear.toString();
        log.info("预占预算接口所传过来的预算年是：{}", budgetYearStr);
        if (4 == budgetYearStr.length()) {
            return "yBudget";
        } else {
            final String monthDay = budgetYearStr.substring(4, 6);
            if (monthDay.contains("0")) {
                return monthDayToMonth(monthDay.substring(1));
            } else {
                return monthDayToMonth(monthDay);
            }
        }
    }

    private static String monthDayToMonth(String month) {
        switch (month) {
            case "1":
                return "m1budget";
            case "2":
                return "m2budget";
            case "3":
                return "m3budget";
            case "4":
                return "m4budget";
            case "5":
                return "m5budget";
            case "6":
                return "m6budget";
            case "7":
                return "m7budget";
            case "8":
                return "m8budget";
            case "9":
                return "m9budget";
            case "10":
                return "m10budget";
            case "11":
                return "m11budget";
            case "12":
                return "m12budget";
            default:
                return "yBudget";
        }
    }

}
