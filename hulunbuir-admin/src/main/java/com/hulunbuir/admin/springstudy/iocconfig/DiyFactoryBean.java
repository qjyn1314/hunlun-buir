package com.hulunbuir.admin.springstudy.iocconfig;

import org.springframework.beans.factory.FactoryBean;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 22:52
 */

public class DiyFactoryBean implements FactoryBean<WomanPerson> {
    @Override
    public WomanPerson getObject() throws Exception {
        return new WomanPerson();
    }

    @Override
    public Class<?> getObjectType() {
        return WomanPerson.class;
    }

    //是否是单例的，true为单例的，false为多例的，每次获取都会创建新的对象
    @Override
    public boolean isSingleton() {
        return false;
    }
}
