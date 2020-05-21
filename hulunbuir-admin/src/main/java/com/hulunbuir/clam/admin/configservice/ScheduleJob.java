package com.hulunbuir.clam.admin.configservice;

import com.hulunbuir.clam.admin.config.RabbitMqQo;
import com.hulunbuir.clam.admin.config.RabbitMqUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain: 定时任务
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/13 14:10
 */
@Component
@Slf4j
public class ScheduleJob {

    /**
     * 测试定时任务-每十秒执行一次
     * 表达式参考：  https://www.bejson.com/othertools/cron/
     *
     * @author wangjunming
     * @since 2020/5/13 16:22
     */
    @Scheduled(cron = "0/15 * * * * ?")
    public void checkState1() {
        log.info(">>>>> cron测试定时任务-每十秒执行一次检查MQ信息开始....");
        RabbitMqUtils.messageTestFanout(new RabbitMqQo());
        log.info(">>>>> cron测试定时任务-每十秒执行一次检查MQ信息结束....");
    }

    /**
     * 测试定时任务-每十秒执行一次
     * 表达式参考：  https://www.bejson.com/othertools/cron/
     *
     * @author wangjunming
     * @since 2020/5/13 16:22
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void checkState2() {
        log.info(">>>>> cron测试定时任务-每十秒执行一次检查MQ信息开始....");
        RabbitMqUtils.messageDevJson(new RabbitMqQo());
        log.info(">>>>> cron测试定时任务-每十秒执行一次检查MQ信息结束....");
    }

}