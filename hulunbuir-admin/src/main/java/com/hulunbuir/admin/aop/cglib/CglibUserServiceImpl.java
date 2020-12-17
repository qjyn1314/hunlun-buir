package com.hulunbuir.admin.aop.cglib;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
public class CglibUserServiceImpl {

    public void addUser() {
        System.out.println("aop之Cglib代理-->目标方法，添加用户...");
    }

}
