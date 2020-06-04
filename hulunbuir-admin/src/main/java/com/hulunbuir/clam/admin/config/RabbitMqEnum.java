package com.hulunbuir.clam.admin.config;

/**
 * <p>
 * explain：创建的MQ信息，交换机和队列
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/13 14:41
 */
public enum RabbitMqEnum {
    //DIRECT(直连)模式
    TEST_MAIL_SEND(RabbitMqUtils.TEST_MAIL_SEND_EXCHANGES, RabbitMqUtils.TEST_MAIL_SEND_QUEUES,1),
    //DIRECT(直连)模式,
    DEV_MAIL_SEND(RabbitMqUtils.DEV_MAIL_SEND_EXCHANGES, RabbitMqUtils.DEV_MAIL_SEND_QUEUES,1),

    //DIRECT(直连)模式,发送者可以查看返回者的消息-比如消费的信息
    PRO_MAIL_SEND(RabbitMqUtils.PRO_MAIL_SEND_EXCHANGES, RabbitMqUtils.PRO_MAIL_SEND_QUEUES,1),

    //FANOUT(广播)模式
    FANOUT_TEST_MAIL_SEND_ONE(RabbitMqUtils.FANOUT_TEST_MAIL_SEND_EXCHANGES, RabbitMqUtils.FANOUT_TEST_MAIL_SEND_QUEUES_ONE,2),
    FANOUT_TEST_MAIL_SEND_TWO(RabbitMqUtils.FANOUT_TEST_MAIL_SEND_EXCHANGES, RabbitMqUtils.FANOUT_TEST_MAIL_SEND_QUEUES_TWO,2),

            ;
    private String exchanges;
    private String queues;
    private Integer exchangesType;

    RabbitMqEnum(String exchanges, String queues, Integer exchangesType) {
        this.exchanges = exchanges;
        this.queues = queues;
        this.exchangesType = exchangesType;
    }

    public String getExchanges() {
        return exchanges;
    }

    public void setExchanges(String exchanges) {
        this.exchanges = exchanges;
    }

    public String getQueues() {
        return queues;
    }

    public void setQueues(String queues) {
        this.queues = queues;
    }

    public Integer getExchangesType() {
        return exchangesType;
    }

    public void setExchangesType(Integer exchangesType) {
        this.exchangesType = exchangesType;
    }
}
