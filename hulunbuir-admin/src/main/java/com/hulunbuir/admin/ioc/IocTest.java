package com.hulunbuir.admin.ioc;

import com.hulunbuir.admin.ioc.iocconfig.DiyFactoryBean;
import com.hulunbuir.admin.ioc.iocconfig.DiyValue;
import com.hulunbuir.admin.ioc.iocconfig.IocConfig;
import com.hulunbuir.admin.ioc.iocconfig.WomanPerson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 21:27
 */
public class IocTest {

    public static void main(String[] args) {
        //创建bean的方式
        /*
         * 向ioc中创建对象:
         *
         * 1、包扫描 扫描包含  @Controller @Service @Component   等，标识为ioc的bean的注解
         * 2、@Bean 注解
         * 3、@import 快速导入一个组件
         *      value  :  DiyImportSelector  实现了 ImportSelector 接口
         *                DiyImportBeanDefreg  实现了 ImportBeanDefinitionRegistrar 接口
         * 4、使用FactoryBean接口，即实现了  FactoryBean  接口
         */
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(IocConfig.class);
        String[] namesForType = applicationContext.getBeanNamesForType(WomanPerson.class);
        System.out.println(Arrays.asList(namesForType));
        String[] beanNamesForType = applicationContext.getBeanDefinitionNames();
        System.out.println(Arrays.asList(beanNamesForType));
        Object diyFactoryBean = applicationContext.getBean("diyFactoryBean");
        System.out.println(diyFactoryBean.getClass());
        Object diyFactoryBeans = applicationContext.getBean("&diyFactoryBean");
        System.out.println(diyFactoryBeans.getClass());
        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);

        // bean中的字段赋值：
        /*
         * 为bean中的字段进行赋值
         * 1、 @Value
         *     基本的数值：@Value("zhangsan")
         *     spel表达式： @Value("#{20-6}")
         *     ${}:表达式：@PropertySource({"classpath:application.properties"})   @Value("${diyname}")
         * 2、 @ConfigurationProperties(prefix = "buir")
         *
         */
        DiyValue diyValue = applicationContext.getBean(DiyValue.class);
        System.out.println(diyValue);
        String diyname = environment.getProperty("diyname");
        System.out.println(diyname);

        //bean的生命周期：
        /*
         * 1、指定初始化方法，销毁方法
         *    @Bean(value = "lisi",initMethod = "init",destroyMethod = "destory")
         *    初始化方法：对象创建完成后，并对字段赋值完成后，调用初始化方法
         *    销毁方法：
         *          单例对象：在容器关闭的时候
         *          多实例对象：容器不会管理这个bean，所以不会调用销毁方法
         * 2、初始化时实现的接口： InitializingBean
         *    销毁时实现的接口： DisposableBean
         *    DiyInitDestroy
         * 3、注解的初始化： @PostConstruct
         *    注解的销毁： @PreDestroy
         * 4、 BeanPostProcessor
         *    初始化之前方法  postProcessBeforeInitialization
         *    初始化之后方法  postProcessAfterInitialization
         */
        applicationContext.close();

    }




}
