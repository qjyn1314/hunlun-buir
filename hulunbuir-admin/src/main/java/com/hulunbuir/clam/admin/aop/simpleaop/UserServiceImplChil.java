package com.hulunbuir.clam.admin.aop.simpleaop;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-24
 */
public class UserServiceImplChil extends UserServiceImpl {
    @Override
    public void addUser() {
        System.out.println("添加用户之前");
        super.addUser();
        System.out.println("添加用户之后");
    }
}
