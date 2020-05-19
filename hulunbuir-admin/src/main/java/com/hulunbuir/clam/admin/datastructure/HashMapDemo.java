package com.hulunbuir.clam.admin.datastructure;

import java.util.HashMap;

/**
 * <p>
 * explain：学习hashmap的源码进行阅读并理解
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/18 21:18
 */
public class HashMapDemo {

  public static void main(String[] args) {
    //study hashmap
//      创建hashmap的三种方式
//      构造一个空的HashMap，具有默认初始容量(16)和默认负载因子(0.75)。
      HashMap<String,Object> map1 = new HashMap<>();
//      构造一个空的HashMap，具有指定的初始容量和缺省负载因子(0.75)。
      HashMap<String,Object> map2 = new HashMap<>(10);
//      构造一个具有指定初始容量和负载因子的HashMap。
      HashMap<String,Object> map3 = new HashMap<>(10,0.36f);
//      使用与指定的Map相同的映射构造一个新的HashMap。创建HashMap时使用缺省负载因子(0.75)和足以容纳指定Map中的映射的初始容量。
      HashMap<String,Object> map5 = new HashMap<>();
      HashMap<String,Object> map4 = new HashMap<>(map5);









  }
}
