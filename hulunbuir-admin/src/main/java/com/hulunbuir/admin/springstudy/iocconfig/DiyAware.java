package com.hulunbuir.admin.springstudy.iocconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/7 23:00
 */
@Component
public class DiyAware implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("自定义组件实现了ApplicationContextAware-->applicationContext="+this.applicationContext);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("自定义组件实现了BeanNameAware-->获取到的名字是："+name);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        //可以为此类中的变量名进行赋值
        String resolveStringValue = resolver.resolveStringValue("nihao ${os.name}");
        System.out.println("自定义组件实现了EmbeddedValueResolverAware-->进行解析字符串："+resolveStringValue);
    }
}
