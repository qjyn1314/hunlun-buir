package com.hulunbuir.clam.admin.aop.factorybean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
public class FactoryAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("aop之spring半自动代理--->执行方法之前");
        Object proceed = methodInvocation.proceed();
        System.out.println("aop之spring半自动代理--->执行方法之后");
        return proceed;
    }
}
