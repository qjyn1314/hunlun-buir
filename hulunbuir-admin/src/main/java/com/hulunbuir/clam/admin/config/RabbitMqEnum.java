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

    TEST_MAIL_SEND(RabbitMqUtils.TEST_MAIL_SEND_EXCHANGES, RabbitMqUtils.TEST_MAIL_SEND_QUEUES),
    DEV_MAIL_SEND(RabbitMqUtils.DEV_MAIL_SEND_EXCHANGES, RabbitMqUtils.DEV_MAIL_SEND_QUEUES);

    private String exchanges;
    private String queues;

    RabbitMqEnum(String exchanges, String queues) {
        this.exchanges = exchanges;
        this.queues = queues;
    }

    public String getExchanges() {
        return this.exchanges;
    }

    public void setExchanges(String exchanges) {
        this.exchanges = exchanges;
    }

    public String getQueues() {
        return this.queues;
    }

    public void setQueues(String queues) {
        this.queues = queues;
    }


}
