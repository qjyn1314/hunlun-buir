package com.hulunbuir.clam.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
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
 * <dependency>
 *      <groupId>org.springframework.boot</groupId>
 *      <artifactId>spring-boot-starter-amqp</artifactId>
 * </dependency>
 * <p>
 * 2.
 * 添加配置信息：
 * #rabbitMq配置信息
 * spring.rabbitmq.host=47.104.78.115
 * spring.rabbitmq.port=5672
 * spring.rabbitmq.username=admin
 * spring.rabbitmq.password=admin
 * #发送确认
 * spring.rabbitmq.publisher-confirms=true
 * #选择确认类型为简单
 * spring.rabbitmq.publisher-confirm-type=simple
 * #是否开启返回模式
 * #spring.rabbitmq.publisher-returns=true
 * #虚拟机主机地址-前提是已经在rabbitmq中进行创建了地址，如果没有创建，则会有空指针异常信息
 * spring.rabbitmq.virtual-host=/test2
 * #链接超时时间
 * spring.rabbitmq.connection-timeout=15000
 * #消费端当前数量-如果出现了消息被消费了两次，则更改此参数为1，
 * spring.rabbitmq.listener.simple.concurrency=1
 * #消费端最大数量
 * spring.rabbitmq.listener.simple.max-concurrency=10
 * #自动签收auto 手动签收manual
 * spring.rabbitmq.listener.simple.acknowledge-mode=manual
 * #限流（海量数据，同时只能过来一条）
 * spring.rabbitmq.listener.simple.prefetch=1
 * <p>
 * 3.应用场景：
 * rabbitmq相当于是一个中间的消息暂存渠道
 * 1.基于服务与服务之间只能单向的引用，调用这一原则，
 * 即，A服务已经是在调用B服务的接口，B服务为A服务进行服务，而这个时候，
 * 又提出新的需求：需要B服务中的调用A服务中的接口来更改状态或者调用接口时，则需要使用rabbitmq
 * <p>
 * 2.程序中出现了异步的请求接口的情况，比如，登录注册发送邮件，发送验证码
 * 出现这样的异步情况，为了不影响主流业务流程的问题，则使用rabbitmq
 * </p>
 *
 *
 * 使用步骤：
 * 1.在RabbitMqUtils类中添加指定的交换机和队列。
 * 2.将添加的交换机和队列变量同步到RabbitMqEnum枚举类中。
 * 3.在RabbitMqUtils类中添加发送消息的静态方法，并指定添加的交换机和队列的key
 * 4.在RabbitMqConsumer类中添加监听指定所添加队列的方法，并接收消息做逻辑处理
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
     * 相关mq创建绑定,DIRECT(直连)模式
     */
    @Bean
    public void MqCreateBinding() {
        //获取MQ枚举所有枚举值
        for (RabbitMqEnum enums : RabbitMqEnum.values()) {
            //枚举值创建交换机
            final Integer exchangesType = enums.getExchangesType();
            DirectExchange directExchange = null;
            FanoutExchange fanoutExchange = null;
            if (Integer.valueOf("1").equals(exchangesType)) {
                try {
                    directExchange = new DirectExchange(enums.getExchanges());
                    amqpAdmin.declareExchange(directExchange);
                } catch (Exception e) {
                    log.error("停止创建MQ，原因：创建MQ的交换机异常：，可能没有创建spring.rabbitmq.virtual-host>>>", e);
                    break;
                }
            } else if (Integer.valueOf("2").equals(exchangesType)) {
                try {
                    fanoutExchange = new FanoutExchange(enums.getExchanges());
                    amqpAdmin.declareExchange(fanoutExchange);
                } catch (Exception e) {
                    log.error("停止创建MQ，原因：创建MQ的交换机异常：，可能没有创建spring.rabbitmq.virtual-host>>>", e);
                    break;
                }
            }
            //枚举值创建消息队列
            Queue paymentCreditQueue = new Queue(enums.getQueues());
            amqpAdmin.declareQueue(paymentCreditQueue);
            //绑定关联关系
            if (Integer.valueOf("1").equals(exchangesType)) {
                Binding binding = BindingBuilder.bind(paymentCreditQueue).to(directExchange).with(enums.getQueues());
                amqpAdmin.declareBinding(binding);
            } else if (Integer.valueOf("2").equals(exchangesType)) {
                Binding binding = BindingBuilder.bind(paymentCreditQueue).to(fanoutExchange);
                amqpAdmin.declareBinding(binding);
            }
        }
    }

    /**
     * 创建初始化RabbitAdmin对象
     *
     * @author wangjunming
     * @since 2020/5/19 10:46
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

}
