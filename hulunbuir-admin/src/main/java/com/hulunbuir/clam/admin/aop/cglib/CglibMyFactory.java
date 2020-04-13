package com.hulunbuir.clam.admin.aop.cglib;

import com.hulunbuir.clam.admin.aop.jdk.MyAdvice;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
public class CglibMyFactory {

    public static CglibUserServiceImpl createUserServasdasdice() {

        //创建实现类的
        final CglibUserServiceImpl userServices = new CglibUserServiceImpl();
        //创建aop前面类
        final MyAdvice advice = new MyAdvice();
        //创建cglib代理的增强核心类，
        Enhancer enhancer = new Enhancer();
        //创建代理类首先要指定父类，也就是目标类(被代理对象)
        enhancer.setSuperclass(CglibUserServiceImpl.class);
        //回调函数，相当于jdk动态代理的 newProxyInstance
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * @param bean 被代理对象
             * @param method 执行方法
             * @param objects 方法参数
             * @param methodProxy 方法的代理
             * @return Object
             * @throws Throwable
             */
            @Override
            public Object intercept(Object bean, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                advice.before();
                /**
                 * 参数1：被代理对象类
                 * 参数2：方法的参数
                 */
                Object invoke = method.invoke(userServices, objects);
                advice.after();
                return invoke;
            }
        });
        //创建代理类
        CglibUserServiceImpl proxyCglibUserService = (CglibUserServiceImpl) enhancer.create();
        return proxyCglibUserService;
    }

}
