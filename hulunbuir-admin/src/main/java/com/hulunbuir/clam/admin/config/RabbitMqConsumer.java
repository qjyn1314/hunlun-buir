package com.hulunbuir.clam.admin.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain: 调用mq信息
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/13 14:53
 */
@Slf4j
@Component
public class RabbitMqConsumer {
    private static final String queues = "test:MAIL_SEND:queues";
    /**
     * MQ消息消费端测试
     */
    @RabbitListener(queues = {queues})
    public void testlis(Message message, Channel channel) throws Exception {
        log.info("测试消费消息：---->>>>----{}", new String(message.getBody()));
        String consumerQueue = message.getMessageProperties().getConsumerQueue();
        log.info("---->>>---------MQ消息消费端的队列是:{} ", consumerQueue);
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        //重新放回消息队列
        /*try {
            int i = 1/0;
        } catch (Exception e) {
            log.info("---->>>-----扔掉消息之后>>>>异常重新放回队列----MQ消息消费端测试: " + consumerQueue);
            channel.basicPublish(RabbitMqEnum.TEST_MAIL_SEND.getExchanges(), consumerQueue, null, message.getBody());
        }*/
    }
}
