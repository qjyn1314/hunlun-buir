package com.hulunbuir.clam.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * explain:rabbitmq配置类
 * <p>
 * 1.
 * 引入： !--整合rabbitmq-->
 *
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-amqp</artifactId>
 * </dependency>
 * <dependency>
 * <groupId>org.springframework.amqp</groupId>
 * <artifactId>spring-amqp</artifactId>
 * <version>2.1.4.RELEASE</version>
 * </dependency>
 *
 *
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/13 14:15
 */
@Slf4j
@Configuration
public class RabbitMqConfig {

    /**
     * RabbitMQ系统管理功能组件
     */
    @Autowired
    public AmqpAdmin amqpAdmin;

    /**
     * 消息转化器-json序列化消息
     */
    @Bean
    public MessageConverter messageConverterByMessage() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 相关mq创建绑定
     */
    @Bean
    public void MqCreateBinding() {
        //获取MQ枚举所有枚举值
        for (RabbitMqEnum enums : RabbitMqEnum.values()) {
            //枚举值创建交换机
            DirectExchange directExchange = null;
            try {
                directExchange = new DirectExchange(enums.getExchanges());
                amqpAdmin.declareExchange(directExchange);
            } catch (Exception e) {
                log.error("停止创建MQ，原因：创建MQ的交换机异常：{}，可能没有创建spring.rabbitmq.virtual-host>>>",e.getMessage());
                break;
            }
            //枚举值创建消息队列
            Queue paymentCreditQueue = new Queue(enums.getQueues());
            amqpAdmin.declareQueue(paymentCreditQueue);

            //绑定关联关系
            Binding binding = BindingBuilder.bind(paymentCreditQueue).to(directExchange).with(enums.getQueues());
            amqpAdmin.declareBinding(binding);
        }
    }

}
