package com.hulunbuir.clam.admin.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain: 调用mq信息，进行消费
 *
 * 在整合的时候出现了错误信息：Channel shutdown: channel error; protocol method:
 * 错误信息参考了：https://blog.csdn.net/qq_38082304/article/details/103049696 已解决
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/13 14:53
 */
@Slf4j
@Component
public class RabbitMqConsumer {

    /**
     * MQ消息消费端消费消息，只监听测试环境的和开发环境的消息接收
     */
    @RabbitListener(queues = {RabbitMqUtils.TEST_MAIL_SEND_QUEUES,RabbitMqUtils.DEV_MAIL_SEND_QUEUES})
    public synchronized void testAndDevMessageConsumer(Message message, Channel channel) throws Exception {
        log.info("消费端所消费消息：---->>>>----{}", new String(message.getBody()));
        final MessageProperties messageProperties = message.getMessageProperties();
        final String consumerQueue = messageProperties.getConsumerQueue();
        final String receivedExchange = messageProperties.getReceivedExchange();
        log.info("---->>>---------MQ消息消费端的交换机是:{} ", receivedExchange);
        log.info("---->>>---------MQ消息消费端的队列是:{} ", consumerQueue);
        boolean flag = true;
        try {
//            int i = 1/0;
        } catch (Exception e) {
            flag = false;
            log.error("消费端的交换机:{},消费端的队列:{}-->异常报错！:{}", receivedExchange, consumerQueue, e);
        }
        if (flag) {
            //扔掉消息
            log.info("---->>>-----扔掉消息");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } else {
            log.info("---->>>-----扔掉消息之后>>>>异常重新放回队列----MQ消息消费端测试: " + consumerQueue);
            //重新放回消息队列
            channel.basicPublish(receivedExchange, consumerQueue, null, message.getBody());
        }
    }

}
