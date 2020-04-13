package com.hulunbuir.clam.admin.design.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
@Slf4j
public class DynaProxy implements InvocationHandler {
    private Object delegate;

    public DynaProxy(Object delegate) {
        this.delegate = delegate;
    }

    public Object bind() {
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(), this.delegate
                        .getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        Object result = null;
        try {
            System.out.println("JVM通过这条语句执行原来的方法(反射机制)--之前");
            AnnotatedType returnType = method.getAnnotatedReturnType();
            System.out.println(returnType.getType());
            result = method.invoke(this.delegate, args);
            System.out.println(result);
            System.out.println("JVM通过这条语句执行原来的方法(反射机制)--之后");
        }catch (Exception e) {
            log.error("代理出现异常信息：{}",e.getMessage());
        }
        return result;
    }
}
