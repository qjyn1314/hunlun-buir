package com.hulunbuir.admin.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * <p>
 * explain: 事件配置类
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/10 21:11
 */
@Configuration
public class SendMailEventMulticaster {

    @Autowired
    private SendMailListener sendMailListener;
    /**
     * 自定义事件广播器
     * @author wangjunming
     * @since 2020/12/10 21:21
     */
    @Bean
    public ApplicationEventMulticaster simpleEventMulticaster(){
        final SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        //向广播器中注册监听器
        eventMulticaster.addApplicationListener(sendMailListener);
        return eventMulticaster;
    }

}
