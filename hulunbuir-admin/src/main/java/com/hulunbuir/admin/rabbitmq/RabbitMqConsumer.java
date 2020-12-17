package com.hulunbuir.admin.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 * explain: 调用mq信息，进行消费
 * <p>
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


    private static MessageProperties getMessageProperties(Message message) {
        return message.getMessageProperties();
    }

    private static String getMessageContent(Message message) {
        String consumerMessage = new String(message.getBody());
        log.info("消费端所消费消息：---->>>>----{}", consumerMessage);
        String consumerQueue = getMessageProperties(message).getConsumerQueue();
        String receivedExchange = getMessageProperties(message).getReceivedExchange();
        String consumerTag = getMessageProperties(message).getConsumerTag();
        log.info("---->>>---------MQ消息消费端的交换机是:{}，队列是:{}，消费Tag：{}", receivedExchange, consumerQueue,consumerTag);
        return consumerMessage;
    }

    private void consumerException(Message message, Exception e) {
        String consumerQueue = getMessageProperties(message).getConsumerQueue();
        String receivedExchange = getMessageProperties(message).getReceivedExchange();
        log.error("消费端的交换机:{},消费端的队列:{}-->异常报错:{}", receivedExchange, consumerQueue, e);
    }

    private static void ackMessage(Message message, Channel channel) {
        log.info("---->>>-----扔掉消息");
        try {
            channel.basicAck(getMessageProperties(message).getDeliveryTag(), false);
        } catch (IOException e) {
            log.error("扔掉消息失败异常：", e);
        }
    }

    private static void publishMessage(Message message, Channel channel) {
        final String consumerQueue = getMessageProperties(message).getConsumerQueue();
        String receivedExchange = getMessageProperties(message).getReceivedExchange();
        log.info("---->>>-----扔掉消息之后>>>>异常重新放回队列----MQ消息消费端测试: " + consumerQueue);
        try {
            channel.basicPublish(receivedExchange, consumerQueue, null, message.getBody());
        } catch (IOException e) {
            log.error("重新放回消息队列失败异常：", e);
        }
    }

    /**
     * DIRECT(直连)模式
     * MQ消息消费端消费消息，只监听开发环境的消息接收
     */
//    @RabbitListener(queues = {RabbitMqUtils.DEV_MAIL_SEND_QUEUES})
    public void devMessageConsumer(Message message, Channel channel) {
        final String messageContent = getMessageContent(message);
        boolean flag = true;
        try {
            final Object object = JSON.toJSON(messageContent);
            log.info("消费端所消费消息的-json：---->>>>----{}", object);
            RabbitMqQo rabbitMqQo = JSONArray.parseObject(object.toString(), RabbitMqQo.class);
            log.info("消费端所消费消息解析后的对象：---->>>>----{}", rabbitMqQo);
        } catch (Exception e) {
            flag = false;
            log.error("解析消息内容失败异常");
            //异常打印
            consumerException(message, e);
        }
        try {
//            int i = 1/0;
        } catch (Exception e) {
            flag = false;
            //异常打印
            consumerException(message, e);
        }
        if (flag) {
            //扔掉消息
            ackMessage(message, channel);
        } else {
            //重新放回消息队列
            publishMessage(message, channel);
        }
    }

    /**
     * DIRECT(直连)模式
     * MQ消息消费端消费消息，只监听测试环境的消息接收
     */
//    @RabbitListener(queues = {RabbitMqUtils.DEV_MAIL_SEND_QUEUES})
    public void testMessageConsumer(Message message, Channel channel) {
        final String messageContent = getMessageContent(message);
        boolean flag = true;
        try {
            //进行解析消息内容
            final Object object = JSON.toJSON(messageContent);
            log.info("消费端所消费消息的-json：---->>>>----{}", object);
            RabbitMqQo rabbitMqQo = JSONArray.parseObject(object.toString(), RabbitMqQo.class);
            log.info("消费端所消费消息解析后的对象：---->>>>----{}", rabbitMqQo);
        } catch (Exception e) {
            flag = false;
            log.error("解析消息内容失败异常：", e);
        }
        try {
//            int i = 1/0;
        } catch (Exception e) {
            flag = false;
            consumerException(message, e);
        }
        if (flag) {
            //扔掉消息
            ackMessage(message, channel);
        } else {
            //重新放回消息队列
            publishMessage(message, channel);
        }
    }



    /**
     * FANOUT(广播)模式
     * MQ消息消费端消费消息，只监听测试环境的广播消息接收
     */
//    @RabbitListener(queues = {RabbitMqUtils.FANOUT_TEST_MAIL_SEND_QUEUES_ONE,RabbitMqUtils.FANOUT_TEST_MAIL_SEND_QUEUES_TWO})
    public void fanoutTestMessageConsumerOne(Message message, Channel channel) {
        final String messageContent = getMessageContent(message);
        boolean flag = true;
        try {
            final Object object = JSON.toJSON(messageContent);
            log.info("消费端所消费消息的-json：---->>>>----{}", object);
            RabbitMqQo rabbitMqQo = JSONArray.parseObject(object.toString(), RabbitMqQo.class);
            log.info("消费端所消费消息解析后的对象：---->>>>----{}", rabbitMqQo);
        } catch (Exception e) {
            flag = false;
            log.error("解析消息内容失败异常");
            //异常打印
            consumerException(message, e);
        }
        try {
//            int i = 1/0;
        } catch (Exception e) {
            flag = false;
            //异常打印
            consumerException(message, e);
        }
        if (flag) {
            //扔掉消息
            ackMessage(message, channel);
        } else {
            //重新放回消息队列
            publishMessage(message, channel);
        }
    }


//    @RabbitListener(queues = {RabbitMqUtils.PRO_MAIL_SEND_QUEUES})
    public static void proMessageConsumer(Message message, Channel channel) {
        final String messageContent = getMessageContent(message);
        boolean flag = true;
        try {
            //进行解析消息内容
            final Object object = JSON.toJSON(messageContent);
            log.info("消费端所消费消息的-json：---->>>>----{}", object);
        } catch (Exception e) {
            flag = false;
            log.error("解析消息内容失败异常：", e);
        }
        if (flag) {
            //扔掉消息
            ackMessage(message, channel);
        } else {
            //重新放回消息队列
            publishMessage(message, channel);
        }
    }




}
