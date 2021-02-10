package com.hulunbuir.admin.springstudy.iocconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <p>
 * explain: 在所有bean创建之前执行
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/9 22:21
 */
@Slf4j
@Component
public class DiyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("在所有bean创建之前执行....容器中一共有几个bean：{}",beanFactory.getBeanDefinitionCount());
        log.info("容器中的bean是那些：{}", Arrays.asList(beanFactory.getBeanDefinitionNames()));
    }
}
