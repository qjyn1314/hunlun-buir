package com.hulunbuir.clam.admin.aop;

import com.hulunbuir.clam.admin.aop.cglib.CglibMyFactory;
import com.hulunbuir.clam.admin.aop.cglib.CglibUserServiceImpl;
import com.hulunbuir.clam.admin.aop.factorybean.FactoryUserService;
import com.hulunbuir.clam.admin.aop.jdk.MyFactory;
import com.hulunbuir.clam.admin.aop.jdk.UserServices;
import com.hulunbuir.clam.admin.aop.simpleaop.UserServiceImplChil;
import com.hulunbuir.clam.admin.aop.springaop.SpringUserService;
import com.hulunbuir.clam.admin.aop.springaopannotation.SpringAnnUserService;
import com.hulunbuir.clam.admin.aop.springaopxml.SpringXmlUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-23
 */
public class AopStudy {

    /**
     * 代理类就是其被代理类所继承的子类
     * <p>
     * target：目标类，需要被代理的类，就是UserService接口类，
     * JoinPoint：连接点，所谓连接点就是那些可能被拦截到的方法，就是UserService中的方法，
     * point：切入点，已经被连接的点，增强点，例如UserService中的addUser()方法
     * advice：通知/增强，就是before，after，例如方法之前的开启事务，方法之后的提交事务
     * pointCut：接入点，
     * <p>
     * jdk的动态代理：
     * 是“装饰者”的简化版使用的前提必须有接口
     * <p>
     * cglib字节码增强：
     * 使用的前提是必须要有类
     * <p>
     * 即在不影响业务的情况下，进行业务的增强
     */
    public static void main(String[] args) {


        UserServiceImplChil chilAop = new UserServiceImplChil();
        chilAop.addUser();

        UserServices userServices = MyFactory.createUserServasdasdice();
        userServices.addUser();

        System.out.println("-================================================");

        CglibUserServiceImpl userServasdasdice = CglibMyFactory.createUserServasdasdice();
        userServasdasdice.addUser();

        System.out.println("-======================springaop的具体实现第一种方式，实现MethodInterceptor，半自动，没有使用扫描==========================");

        ApplicationContext ac = new ClassPathXmlApplicationContext("com\\hulunbuir\\clam\\admin\\aop\\factorybean\\applicationContext.xml");
        FactoryUserService factoryUserService = (FactoryUserService) ac.getBean("proxyFactoryBean");
        factoryUserService.addUser();

        System.out.println("-======================springaop的具体实现第二种方式，全自动，进行扫描==========================");

        ApplicationContext ace = new ClassPathXmlApplicationContext("com\\hulunbuir\\clam\\admin\\aop\\springaop\\applicationContext.xml");
        SpringUserService springUserService = (SpringUserService) ace.getBean("springUserService");
        springUserService.addUser();

        System.out.println("-======================springaop的具体实现第二种方式，全自动，灵活配置切点==========================");
        ApplicationContext aces = new ClassPathXmlApplicationContext("com\\hulunbuir\\clam\\admin\\aop\\springaopxml\\applicationContext.xml");
        SpringXmlUserService springXmlUserService = (SpringXmlUserService) aces.getBean("springXmlUserServiceId");
        springXmlUserService.addUser("SpringXmlService");

        System.out.println("-======================springaop的具体实现第三种方式，全自动，注解形式的SpringAop==========================");

        ApplicationContext acs = new ClassPathXmlApplicationContext("com\\hulunbuir\\clam\\admin\\aop\\springaopannotation\\applicationContext.xml");
        SpringAnnUserService springAnnUserService = (SpringAnnUserService) acs.getBean("springAnnUserService");
        springAnnUserService.addUser("SpringXmlService");

    }

}
