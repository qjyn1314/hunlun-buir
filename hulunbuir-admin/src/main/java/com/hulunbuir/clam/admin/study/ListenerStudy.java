package com.hulunbuir.clam.admin.study;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Explain:listener 监听器巩固
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-19
 */
public class ListenerStudy {

    public static void main(String[] args) throws Exception{

        ListenerStudy listener = new ListenerStudy();

        Object clone = listener.clone();

        System.out.println(clone);

        Map<String, String> stringMap = Collections.synchronizedMap(new HashMap<String, String>(12));




        handleMessage();
    }

    /**
     * 1、观察者设计模式
     * 2、监听器具体的实现了观察者设计模式
     *
     * session的销毁有两种方式：
     *   设置session的过期时间，默认过期时间是三十分钟
     *   调用session的  session.invalidate();  方法也能进行销毁
     *
     *
     *
     *
     */
    static void handleMessage(){
        System.out.println("被观察者处理信息......");
    };

}
