package com.hulunbuir.admin.worktest;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * explain:
 *
 * @author wangjunming
 * @since 2020/5/18 15:07
 */
@Slf4j
public class CatTest {

  public static void main(String[] args) {
    //测试lambda表达式
    List<Cat> catListas = new ArrayList<>();
    catListas.add(new Cat(1, "张三1", 121));
    catListas.add(new Cat(2, "张三2", 122));

    List<Cat> catList = new ArrayList<>();
    catList.add(new Cat(1, "张三1", 121));
    catList.add(new Cat(2, "张三2", 122));
    catList.add(new Cat(3, "张三3", 123));
    catList.add(new Cat(4, "张三4", 124));
    catList.add(new Cat(5, "张三5", 125));

    catList = catList.stream().filter(cat -> !catListas.contains(cat)).collect(Collectors.toList());
    System.out.println(catList);

    Calendar nowDate = Calendar.getInstance();
    nowDate.set(Calendar.DAY_OF_YEAR,12);
//    nowDate.set(Calendar.DAY_OF_MONTH,10);
//    nowDate.set(Calendar.DAY_OF_WEEK,10);
    final Date time = nowDate.getTime();

    final String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    log.info("format:{}",format);

    final int diffDate = getDiffDate(time);
    System.out.println(diffDate);


  }


  public static int getDiffDate(Date arriveTime) {
    Calendar nowDate = Calendar.getInstance();
    return (int) ChronoUnit.DAYS.between(nowDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), arriveTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
  }


}
