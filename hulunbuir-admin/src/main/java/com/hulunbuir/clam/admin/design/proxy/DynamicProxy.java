package com.hulunbuir.clam.admin.design.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 学习的动态代理
 * @date：2019/1/9
 * @author：Wjunming
 */
public class DynamicProxy implements InvocationHandler {
    private Logger logger = LoggerFactory.getLogger(DynamicProxy.class);

    private Object obj = null;

    public Object bind(Object obj) {
        this.obj =  obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("进入的方法---方法名："+method.getName()+"---参数是："+Arrays.toString(args));
        Object invoke = method.invoke(this.obj, args);
        logger.info("结束的方法---方法名："+method.getName()+"---结果是："+invoke);
        return invoke;
    }

}
