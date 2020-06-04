package com.hulunbuir.clam.admin.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <p>
 * explain:使用mq服务
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/13 14:42
 */
@Slf4j
@Component
public class RabbitMqUtils {
//    DIRECT(直连)模式，通俗说是一个交换机绑定一个队列
    public static final String DEV_MAIL_SEND_EXCHANGES = "dev:MAIL_SEND:exchanges",DEV_MAIL_SEND_QUEUES = "dev:MAIL_SEND:queues";
//    DIRECT(直连)模式，通俗说是一个交换机绑定一个队列
    public static final String TEST_MAIL_SEND_EXCHANGES = "test:MAIL_SEND:exchanges",TEST_MAIL_SEND_QUEUES = "test:MAIL_SEND:queues";
//    DIRECT(直连)模式，通俗说是一个交换机绑定一个队列
    public static final String PRO_MAIL_SEND_EXCHANGES = "pro:MAIL_SEND:exchanges",PRO_MAIL_SEND_QUEUES = "pro:MAIL_SEND:queues";
//   FANOUT(广播)模式，通俗说是一个交换机绑定两个或多个队列
    public static final String FANOUT_TEST_MAIL_SEND_EXCHANGES = "fanout_test:MAIL_SEND:exchanges",FANOUT_TEST_MAIL_SEND_QUEUES_ONE = "fanout_test:MAIL_SEND:queues_one",FANOUT_TEST_MAIL_SEND_QUEUES_TWO = "fanout_test:MAIL_SEND:queues_two";

    private static RabbitTemplate rabbitTem;

    /**
     * 初始化发送MQ的基本信息
     *
     * @author wangjunming
     * @since 2020/5/19 10:19
     */
    public RabbitMqUtils(RabbitTemplate rabbitTemplate) {
        rabbitTem = rabbitTemplate;
        log.info("初始化MQ的信息：" + JSON.toJSONString(rabbitTem, SerializerFeature.PrettyFormat));
    }

    /**
     * 测试环境发送消息 DIRECT(直连)模式
     *
     * @author wangjunming
     * @since 2020/5/13 16:07
     */
    public static void messageTest(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        try {
            rabbitTem.convertAndSend(TEST_MAIL_SEND_EXCHANGES, TEST_MAIL_SEND_QUEUES, message, correlationData);
        } catch (AmqpException e) {
            log.error("MQ消息发送失败：{}，可能没有创建spring.rabbitmq.virtual-host>>>", e.getMessage());
        }
    }

    /**
     * 开发环境发送消息 DIRECT(直连)模式
     *
     * @author wangjunming
     * @since 2020/5/13 16:07
     */
    public static void messageDevJson(RabbitMqQo rabbitMqQo) {
        rabbitMqQo.setUserName("仼少");
        rabbitMqQo.setUserMail("qjyn1314@163.com");
        rabbitMqQo.setUserAge("25");
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        try {
            rabbitTem.convertAndSend(DEV_MAIL_SEND_EXCHANGES, DEV_MAIL_SEND_QUEUES, rabbitMqQo, correlationData);
        } catch (AmqpException e) {
            log.error("MQ消息发送失败：{}，可能没有创建spring.rabbitmq.virtual-host>>>", e.getMessage());
        }
    }


    /**
     * 测试环境发送消息 FANOUT(广播)模式
     *
     * @author wangjunming
     * @since 2020/5/13 16:07
     */
    public static void messageTestFanout(RabbitMqQo rabbitMqQo) {
        rabbitMqQo.setUserName("仼少");
        rabbitMqQo.setUserMail("qjyn1314@163.com");
        rabbitMqQo.setUserAge("25");
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        try {
            rabbitTem.convertAndSend(FANOUT_TEST_MAIL_SEND_EXCHANGES, "", rabbitMqQo, correlationData);
        } catch (AmqpException e) {
            log.error("MQ消息发送失败：{}，可能没有创建spring.rabbitmq.virtual-host>>>", e.getMessage());
        }
    }

    /**
     *
     * @author wangjunming
     * @since 2020/5/13 16:07
     */
    public static void messageProdFanout(String proMessage) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        try {
            rabbitTem.convertAndSend(PRO_MAIL_SEND_EXCHANGES, PRO_MAIL_SEND_QUEUES, proMessage, correlationData);
        } catch (AmqpException e) {
            log.error("MQ消息发送失败：{}，可能没有创建spring.rabbitmq.virtual-host>>>", e.getMessage());
        }
    }

}