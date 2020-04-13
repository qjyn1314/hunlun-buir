package com.hulunbuir.clam.admin.aop.springaopannotation;

import org.springframework.stereotype.Service;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
@Service("springAnnUserService")
public class SpringAnnUserServiceImpl implements SpringAnnUserService {
    @Override
    public String addUser(String name) {
        System.out.println("aop之springAnn全自动--->创建用户!!!"+name);
        return name;
    }
}
