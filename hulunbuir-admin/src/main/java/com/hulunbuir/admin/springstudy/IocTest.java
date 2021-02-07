package com.hulunbuir.admin.springstudy;

import com.hulunbuir.admin.springstudy.iocconfig.*;
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
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前spring的运行环境
        applicationContext.getEnvironment().setActiveProfiles("test","dev");
        //找到配置类
        applicationContext.register(IocConfig.class);
        //刷新当前环境
        applicationContext.refresh();

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

        /**
         *
         * 自动装配：
         *      spring利用依赖注入（DI），完成IOC容器中各个组件的依赖关系赋值；
         *
         * @Autowired 自动注入（推荐使用，spring原生，）
         *      1、默认：优先按照类型去容器中寻找对应的组件,applicationContext.getBean(WomanPersonController.class)
         *      2、当找到了多个相同类型的组件，再将属性的名称作为组件的ID去容器中查找,applicationContext.getBean("diyValueAutowired")
         *      3、@Qualifier("diyValueAutowired")  指定主要装配的组件ID，而不是使用属性名
         *      4、自动装配一定会将属性值进行赋值，没有就会报错，可以使用  @Autowired(required = false)  即使没有赋值也不会启动报错
         *      5、@Primary  让spring进行自动装配的时候，默认使用的首选的bean；也可以继续使用，@Qualifier进行指定所需要的bean
         * @Resource 自动注入
         *      1、默认：按照组件名称进行装配
         *      2、没有能支持 @Primary 和没有  @Autowired(required = false)
         * @Inject 自动注入
         *      1、默认： 与 @Autowired 注解一样，默认支持 @Primary ，没有 @Autowired(required = false)
         *
         *  特殊的 @Autowired ：
         *    1、 @Autowired  放在 set 方法上，从容器中拿到bean，赋值到set方法中
         *    2、 @Autowired  放在 有参构造方法中，从容器中拿到bean，赋值到构造器中的
         *    3、 @Autowired  放在  有参构造的参数前面，从容器中拿到bean，赋值到构造器中的
         *
         * 自定义组件实现 Aware 接口,主要是为了使用spring底层一些组件
         *  例如：
         *      ApplicationContextAware  接口可获取ioc容器
         *
         */
        WomanPersonController personController = applicationContext.getBean(WomanPersonController.class);
        System.out.println(personController);
        DiyAware diyAware = applicationContext.getBean(DiyAware.class);
        System.out.println(diyAware);

        /*
         * @Profile("dev")
         * 可放在 创建bean的方法上  根据当前环境创建不同的bean对象
         * 可放在 类上，根据当前环境判断当前类是否创建
         * -Dspring.profiles.active=test  命令行参数执行的值  标志为当前所执行的环境是什么
         */
        DiyProfileByDB diyProfileDb = applicationContext.getBean(DiyProfileByDB.class);
        System.out.println(diyProfileDb);
        String[] beanNamesForTypeByDb = applicationContext.getBeanNamesForType(DiyProfileByDB.class);
        System.out.println(Arrays.asList(beanNamesForTypeByDb));


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
