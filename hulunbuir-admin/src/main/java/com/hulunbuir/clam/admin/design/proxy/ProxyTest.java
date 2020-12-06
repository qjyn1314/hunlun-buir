package com.hulunbuir.clam.admin.design.proxy;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/6 17:43
 */
public class ProxyTest {

    public static void main(String[] args) {
        //---第一种
        DynamicProxy dy = new DynamicProxy();
        HelloServiceImpl dynamicService = new HelloServiceImpl();
        IHello bind = (IHello)dy.bind(dynamicService);
        bind.sayHello("mr.wang");
        //---第二种
        HelloServiceImpl dynamicServices = new HelloServiceImpl();
        DynaProxy dys = new DynaProxy(dynamicServices);
        IHello binds = (IHello)dys.bind();
        String k = "";
        k = binds.function("\t\t kokoko   mr.wang  \t\t  ",k);
        binds.sayHello("谢天谢地你来了！！！");
        System.out.println(k);
        //---第三种
        FoodService foodService = new FoodServiceProxy();
        foodService.scrambledEggWithTomato();

    }

}
