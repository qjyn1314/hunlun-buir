package com.hulunbuir.admin.springstudy.iocconfig;

import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 21:31
 */
@PropertySource(value = {"classpath:application.properties"})
@Conditional({WindowsCondition.class,LinuxCondition.class})
@Configuration(proxyBeanMethods = false)
@ComponentScan(value = "com.hulunbuir.admin.springstudy.iocconfig",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {RestController.class})
}
)
@Import({WomanPersonController.class, DiyImportSelector.class,DiyImportBeanDefreg.class})
public class IocConfig {

    @Scope("prototype")
    @Lazy
    @Bean("zhangsan")
    public WomanPerson zhangsan(){
        return new WomanPerson("zhangsan",20);
    };

    //根据不同的条件是否创建bean
    @Conditional({WindowsCondition.class})
    @Bean(value = "lisi",initMethod = "init",destroyMethod = "destroy")
    public WomanPerson lisi(){
        return new WomanPerson("lisi",20);
    };

    @Conditional({LinuxCondition.class})
    @Bean("wangwu")
    public WomanPerson wangwu(){
        return new WomanPerson("wangwu",20);
    };

    @Bean
    public DiyFactoryBean diyFactoryBean(){
        return new DiyFactoryBean();
    }

    @Bean
    public DiyInitDestroy diyInitDestroy(){
        return new DiyInitDestroy();
    }

    @Bean
    public DiyBeanPostProcessor diyBeanPostProcessor(){
        return new DiyBeanPostProcessor();
    }

    @Bean("diyValueAutowired")
    public DiyValue diyValueAutowired(){
        DiyValue diyValue = new DiyValue();
        diyValue.setId(10L);
        return diyValue;
    }


}
