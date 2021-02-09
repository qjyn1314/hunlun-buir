package com.hulunbuir.admin.springstudy;

import com.hulunbuir.admin.springstudy.aopconfig.AopConfig;
import com.hulunbuir.admin.springstudy.aopconfig.SpringAopUserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/7 23:59
 */
public class AopTest {

    /**
     * AOP:动态代理
     *     指程序在运行的期间动态的将某段代码切入到指定方法的指定位置中进行运行的编程方式；
     *  1、
     *  具体实例： https://gitee.com/likun_557/spring-aop-demo.git
     *
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);

        SpringAopUserService aopUserService = applicationContext.getBean(SpringAopUserService.class);
        String addUser = aopUserService.addUser("添加用户");
        System.out.println(addUser);


    }








}
