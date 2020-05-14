package com.hulunbuir.clam.admin.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * <p>
 * explain:使用mq
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/13 14:42
 */
@Slf4j
@Component
public class RabbitMqUtils {

    private static RabbitTemplate rabbitTem;
    private final CachingConnectionFactory connectionFactory;
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqUtils(CachingConnectionFactory connectionFactory, RabbitTemplate rabbitTemplate) {
        this.connectionFactory = connectionFactory;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 测试发送消息
     *
     * @author wangjunming
     * @since 2020/5/13 16:07
     */
    public static void messageTest(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        try {
            rabbitTem.convertAndSend(RabbitMqEnum.TEST_MAIL_SEND.getExchanges(), RabbitMqEnum.TEST_MAIL_SEND.getQueues(), message,correlationData);
        } catch (AmqpException e) {
            log.error("MQ消息发送失败：{}，可能没有创建spring.rabbitmq.virtual-host>>>",e.getMessage());
        }
    }

    /**
     * 初始化MQ
     *
     * @author wangjunming
     * @since 2020/5/13 16:14
     */
    @PostConstruct
    private void init() {
        rabbitTem = rabbitTemplate;
        log.info("初始化MQ的信息：" + JSON.toJSONString(connectionFactory, SerializerFeature.PrettyFormat) + "<<<---\n--->>>" + JSON.toJSONString(rabbitTem,SerializerFeature.PrettyFormat));
    }


}
