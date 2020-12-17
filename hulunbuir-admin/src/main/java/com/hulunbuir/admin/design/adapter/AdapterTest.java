package com.hulunbuir.admin.design.adapter;

import com.hulunbuir.admin.design.adapter.clazz.SomeTargetAdapter;
import com.hulunbuir.admin.design.adapter.def.AdapterInterface;
import com.hulunbuir.admin.design.adapter.def.AdapterSpecific;
import com.hulunbuir.admin.design.adapter.obj.Cock;
import com.hulunbuir.admin.design.adapter.obj.CockAdapter;
import com.hulunbuir.admin.design.adapter.obj.WildCock;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Explain:适配器的使用，本质就是将两个毫不相关的事物进行组合，一方适配另一方，取代另一方
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-24 16:22
 */
@Slf4j
public class AdapterTest {

    public static void main(String[] args) {

        //--默认适配器模式，即，具体实现类继承了抽象类，抽象类实现了接口。使用接口对应的具体实现，则使用到了默认的适配器
        AdapterInterface adapterSpecific = new AdapterSpecific();
        final String adapterSend = adapterSpecific.adapterSend();
        log.info("adapterSend:{}", adapterSend);
        //--对象适配器模式，即，两个不同的接口中有不同的方法，
        Cock cock = new WildCock();
        CockAdapter cockAdapter = new CockAdapter(cock);
        cockAdapter.quack();
        cockAdapter.fly();
        //--类适配器模式，即两个不同的事物，一个在接口里，一个在类中，则创建一个类来进行继承类，实现接口，这样此类就成为了一个适配器类
        SomeTargetAdapter target = new SomeTargetAdapter();
        target.doSomeThingOne();
        target.doSomeThingTwo();
        target.doSomeThingThree();
        target.doSomeThingFour();

    }

}