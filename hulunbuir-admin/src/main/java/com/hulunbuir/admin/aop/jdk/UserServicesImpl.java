package com.hulunbuir.admin.aop.jdk;


/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
public class UserServicesImpl implements UserServices {
    @Override
    public void addUser() {
        System.out.println("aop之JDK代理-->目标方法，添加用户...");
    }
}
