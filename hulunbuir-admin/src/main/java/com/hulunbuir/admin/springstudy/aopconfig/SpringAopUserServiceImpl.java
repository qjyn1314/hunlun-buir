package com.hulunbuir.admin.springstudy.aopconfig;

import org.springframework.stereotype.Service;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
@Service("springAopUserService")
public class SpringAopUserServiceImpl implements SpringAopUserService {
    @Override
    public String addUser(String name) {
        System.out.println("@EnableAspectJAutoProxy--->创建用户名="+name);
        return name;
    }
}
