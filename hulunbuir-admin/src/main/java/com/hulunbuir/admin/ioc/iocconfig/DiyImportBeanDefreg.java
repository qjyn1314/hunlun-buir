package com.hulunbuir.admin.ioc.iocconfig;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.repository.config.RepositoryConfigurationDelegate;

/**
 * <p>
 * explain: 手动注册bean到容器中bean或者删除已加载的bean
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 22:36
 */
public class DiyImportBeanDefreg implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean lisi = registry.containsBeanDefinition("lisi");
        if(lisi){
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(WomanPerson.class);
            registry.registerBeanDefinition("wangwu",rootBeanDefinition);
        }
    }

}
