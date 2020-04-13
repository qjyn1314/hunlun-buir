package com.hulunbuir.clam.admin.aop.springaopxml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
public class SpringXmlAdvice {

    public void before(JoinPoint point) {
        String name = point.getSignature().getName();
        Object[] args = point.getArgs();
        System.out.println("aop之springXml全自动--->执行方法before-前置通知-->" + name);
        System.out.println("aop之springXml全自动--->执行方法before-前置通知-->" + Arrays.asList(args));
    }

    public void after(JoinPoint point) {
        String name = point.getSignature().getName();
        Object[] args = point.getArgs();
        System.out.println("aop之springXml全自动--->执行方法after-后置通知-->" + name);
        System.out.println("aop之springXml全自动--->执行方法after-后置通知-->" + Arrays.asList(args));
    }

    public void afterReturning(JoinPoint point, Object object) {
        String name = point.getSignature().getName();
        Object[] args = point.getArgs();
        System.out.println("aop之springXml全自动--->执行方法afterReturning-执行返回通知-->" + name);
        System.out.println("aop之springXml全自动--->执行方法afterReturning-执行返回通知-->" + Arrays.asList(args));
        System.out.println("aop之springXml全自动--->执行方法afterReturning-执行返回通知-->" + object);
    }

    public Object around(ProceedingJoinPoint point) throws Throwable{
        String name = point.getSignature().getName();
        Object[] args = point.getArgs();
        System.out.println("aop之springXml全自动--->执行方法around-执行环绕通知-->" + name);
        Object proceed = point.proceed();
        System.out.println("aop之springXml全自动--->执行方法around-执行环绕通知-->" + Arrays.asList(args));
        return proceed;
    }

    public void afterThrowing(JoinPoint point, Throwable throwable){
        String name = point.getSignature().getName();
        Object[] args = point.getArgs();
        System.out.println("aop之springXml全自动--->执行方法afterThrowing-抛出异常通知-->" + name);
        System.out.println("aop之springXml全自动--->执行方法afterThrowing-抛出异常通知-->" + Arrays.asList(args));
        System.out.println("aop之springXml全自动--->执行方法afterThrowing-抛出异常通知-->"+throwable);
    }


}
