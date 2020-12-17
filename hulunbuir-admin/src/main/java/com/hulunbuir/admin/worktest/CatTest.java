package com.hulunbuir.admin.worktest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * explain:
 *
 * @author wangjunming
 * @since 2020/5/18 15:07
 */
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




  }
}
