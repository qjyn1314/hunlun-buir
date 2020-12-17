package com.hulunbuir.admin.aop.springaop;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
public class SpringUserServiceImpl implements SpringUserService {
    @Override
    public void addUser() {
        System.out.println("aop之spring全自动代理--->创建用户!!!");
    }
}
