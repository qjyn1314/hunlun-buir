package com.hulunbuir.clam.admin.design.adapter;

/**
 * <p>
 * Explain:适配器的使用
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 16:22
 */
public class AdapterDemo {

    public static void main(String[] args) {

        //--默认适配器模式，即，具体实现类继承了抽象类，抽象类实现了接口。使用接口对应的具体实现，则使用到了默认的适配器
        AdapterInterface adapterSpecific = new AdapterSpecific();
        final String adapterSend = adapterSpecific.adapterSend();
        System.out.println(adapterSend);
        //


    }

}