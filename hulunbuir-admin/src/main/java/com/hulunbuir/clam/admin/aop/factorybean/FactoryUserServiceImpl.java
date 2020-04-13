package com.hulunbuir.clam.admin.aop.factorybean;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
public class FactoryUserServiceImpl implements FactoryUserService{
    @Override
    public void addUser() {
        System.out.println("aop之spring半自动代理--->创建用户!!!");
    }
}
