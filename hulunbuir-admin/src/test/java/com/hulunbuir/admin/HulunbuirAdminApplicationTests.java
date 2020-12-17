package com.hulunbuir.admin;

import com.hulunbuir.admin.controller.SupplierPo;
import com.hulunbuir.admin.design.factory_strategy.Message;
import com.hulunbuir.admin.design.factory_strategy.MessageFactory;
import com.hulunbuir.admin.design.proxy.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HulunbuirAdminApplicationTests {

    @Autowired
    private Message message;

    @Autowired
    private MessageFactory messageFactoryService;
    @Autowired
    private MessageFactory factoryService;

    @Autowired
    @Qualifier("foodServiceProxy")
    private FoodService foodService;

    //测试工厂模式实践示例
    @Test
    void contextLoadss() {
    }

    //测试代理模式实践示例
    @Test
    void contextLoadsProxy() {
        foodService.scrambledEggWithTomato();
    }
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Test
    void getAppKey(){
         List<SupplierPo> supplierPos = new ArrayList<>();
        SupplierPo supplierPo = new SupplierPo("15321355715","A0001","username001");
        supplierPos.add(supplierPo);
        SupplierPo supplierPo1 = new SupplierPo("15321355715","A0001","username001");
        supplierPos.add(supplierPo1);
        supplierPos.forEach( supplier ->{
            listOperations.rightPush(supplier.getPhone(),supplier);
            System.out.println(supplier.getPhone());
        });
        supplierPos.forEach( supplier ->{
            final Object leftPop = listOperations.rightPop(supplier.getPhone());
            System.out.println(leftPop);
        });
    }






}
