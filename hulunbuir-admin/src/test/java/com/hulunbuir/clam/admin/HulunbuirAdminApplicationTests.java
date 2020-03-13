package com.hulunbuir.clam.admin;

import com.hulunbuir.clam.admin.design.factory.messagefactory.Message;
import com.hulunbuir.clam.admin.design.factory.messagefactory.MessageFactoryService;
import com.hulunbuir.clam.admin.design.factory.messagefactory.MessageFactoryServiceImpl;
import com.hulunbuir.clam.admin.design.proxy.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HulunbuirAdminApplicationTests {

    @Autowired
    private Message message;

    @Autowired
    private MessageFactoryServiceImpl messageFactoryService;
    @Autowired
    private MessageFactoryService factoryService;

    @Autowired
    @Qualifier("foodServiceProxy")
    private FoodService foodService;

    //测试工厂模式实践示例
    @Test
    void contextLoadss() {
        Long tenId = 12111L;
        String contentType = "contentType--__";
        String paramsStr = "assdadd_asdad";
        message.sendMessage(tenId, contentType, paramsStr);
        Message messageServiceImplByType = factoryService.getMessageServiceImplByType();
        messageServiceImplByType.sendMessage(tenId, contentType, paramsStr);
        Message messageServiceImpl = messageFactoryService.getMessageServiceImplByType();
        messageServiceImpl.sendMessage(tenId, contentType, paramsStr);
    }

    //测试代理模式实践示例
    @Test
    void contextLoadsProxy() {
        foodService.scrambledEggWithTomato();
    }

    @Test
    void getAppKey(){
        String appKey = "testConfig";
        System.out.println();
        System.out.printf("AppKey:%s",appKey);
        System.out.println();
    }





}
