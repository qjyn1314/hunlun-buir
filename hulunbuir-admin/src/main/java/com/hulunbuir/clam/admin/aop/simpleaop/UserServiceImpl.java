package com.hulunbuir.clam.admin.aop.simpleaop;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-24
 */
public class UserServiceImpl implements UserService{


    @Override
    public void addUser() {
        System.out.println("UserServiceImpl---->添加用户");
    }
}
