package com.hulunbuir.clam.admin.aop.jdk;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
public class MyAdvice {


    public void before(){
        System.out.println("前置通知");
    }

    public void after(){
        System.out.println("后置通知");
    }

}
