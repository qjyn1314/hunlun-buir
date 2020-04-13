package com.hulunbuir.clam.admin.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
public class MyFactory {

    public static UserServices createUserServasdasdice() {

        //创建接口类的
        final UserServices userServices = new UserServicesImpl();
        //创建aop前面类
        final MyAdvice advice = new MyAdvice();
        //创建代理类
        /**
         * loader:类加载器，一般情况下，是指当前类的类加载器；
         * Class[] interface:目标代理的接口类；
         * InvocationHandle:处理类，接口必须要有实现类，就是被代理接口的实现类，一般是匿名内部类
         */
        UserServices userServicesProxy = (UserServices) Proxy.newProxyInstance(MyFactory.class.getClassLoader(), userServices.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * @param proxy 代理对象
             * @param method 执行的方法
             * @param args 方法中所带的参数
             * @return result 方法执行的结果
             * @throws Throwable 线程异常
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                advice.before();
                Object result = method.invoke(userServices, args);
                advice.after();
                return result;
            }
        });
        return userServicesProxy;
    }

}
