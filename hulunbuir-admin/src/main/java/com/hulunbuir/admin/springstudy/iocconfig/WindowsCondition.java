package com.hulunbuir.admin.springstudy.iocconfig;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 22:11
 */
public class WindowsCondition implements Condition {
    /**
     *
     * @param conditionContext 判断条件能使用的上下文环境
     * @param annotatedTypeMetadata 注释信息
     * @return 处理规则
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        //bean工厂
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        //环境变量信息
        Environment environment = conditionContext.getEnvironment();
        String property = environment.getProperty("os.name");

        if(property.contains("Window")){
            return true;
        }
        //获取到bean定义的注册器
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        MergedAnnotations annotations = annotatedTypeMetadata.getAnnotations();

        return false;
    }
}
