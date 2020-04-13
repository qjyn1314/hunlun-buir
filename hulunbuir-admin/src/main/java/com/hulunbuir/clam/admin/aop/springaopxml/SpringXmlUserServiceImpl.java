package com.hulunbuir.clam.admin.aop.springaopxml;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
public class SpringXmlUserServiceImpl implements SpringXmlUserService {
    @Override
    public String addUser(String name) {
        System.out.println("aop之springXml全自动--->创建用户!!!"+name);
        return name;
    }
}
