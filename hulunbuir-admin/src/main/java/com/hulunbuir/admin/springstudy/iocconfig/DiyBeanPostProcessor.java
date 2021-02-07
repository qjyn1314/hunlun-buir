package com.hulunbuir.admin.springstudy.iocconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 23:42
 */
public class DiyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("实现了-BeanPostProcessor-初始化前：postProcessBeforeInitialization-"+bean+"名称："+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("实现了-BeanPostProcessor-初始化后：postProcessAfterInitialization-"+bean+"-名称："+beanName);
        return bean;
    }
}
