package com.hulunbuir.clam.admin.configservice;

import com.hulunbuir.clam.admin.config.RabbitMqUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/13 14:10
 */
@Component
@Slf4j
public class ScheduleJob {


    /**
     * 测试定时任务-每两秒执行一次
     * 参考： https://www.bejson.com/othertools/cron/
     *
     * @author wangjunming
     * @since 2020/5/13 16:22
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void checkState1() {
        RabbitMqUtils.messageTest("进行测试使用MQ进行发送消息，并进行监听！！");
        log.info(">>>>> cron测试定时任务-每两秒执行一次检查开始....");
        log.info(">>>>> cron测试定时任务-每两秒执行一次检查完成....");
    }




}
