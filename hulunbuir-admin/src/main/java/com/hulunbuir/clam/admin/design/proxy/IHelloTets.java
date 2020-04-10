package com.hulunbuir.clam.admin.design.proxy;

/**
 * <p>
 * 说明：
 * </p >
 *
 * @author wangjunming
 * @since 2019-07-11
 */
public class IHelloTets {


    public static void main(String[] args) {
        /*DynamicProxy dy = new DynamicProxy();
        MyDynamicServiceImpl dynamicService = new MyDynamicServiceImpl();
        IHello bind = (IHello)dy.bind(dynamicService);
        bind.sayHello("mr.wang");*/
        MyDynamicServiceImpl dynamicServices = new MyDynamicServiceImpl();
        DynaProxy dys = new DynaProxy(dynamicServices);
        IHello binds = (IHello)dys.bind();
        String k = "";
        k = binds.function("\t\t kokoko   mr.wang  \t\t  ",k);
        binds.sayHello("谢天谢地你来了！！！");
        System.out.println(k);
    }





}
