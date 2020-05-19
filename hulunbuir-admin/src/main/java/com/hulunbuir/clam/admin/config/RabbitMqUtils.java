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

    public static final String DEV_MAIL_SEND_QUEUES = "dev:MAIL_SEND:queues", DEV_MAIL_SEND_EXCHANGES = "dev:MAIL_SEND:exchanges";
    public static final String TEST_MAIL_SEND_QUEUES = "test:MAIL_SEND:queues", TEST_MAIL_SEND_EXCHANGES = "test:MAIL_SEND:exchanges";

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
     * 测试环境发送消息
     *
     * @author wangjunming
     * @since 2020/5/13 16:07
     */
    public static void messageTest(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        try {
            rabbitTem.convertAndSend(RabbitMqEnum.TEST_MAIL_SEND.getExchanges(), RabbitMqEnum.TEST_MAIL_SEND.getQueues(), message, correlationData);
        } catch (AmqpException e) {
            log.error("MQ消息发送失败：{}，可能没有创建spring.rabbitmq.virtual-host>>>", e.getMessage());
        }
    }

    /**
     * 开发环境发送消息
     *
     * @author wangjunming
     * @since 2020/5/13 16:07
     */
    public static void messageDev(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        try {
            rabbitTem.convertAndSend(RabbitMqEnum.DEV_MAIL_SEND.getExchanges(), RabbitMqEnum.DEV_MAIL_SEND.getQueues(), message, correlationData);
        } catch (AmqpException e) {
            log.error("MQ消息发送失败：{}，可能没有创建spring.rabbitmq.virtual-host>>>", e.getMessage());
        }
    }


}
