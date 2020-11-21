package com.hulunbuir.clam.admin.configservice;

import com.hulunbuir.clam.admin.config.RabbitMqQo;
import com.hulunbuir.clam.admin.config.RabbitMqUtils;
import com.hulunbuir.clam.common.config.RedisService;
import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    RedisService redisService;
    @Autowired
    private RedisTemplate<String, Object> redisStrKeyTemplate;

    /**
     * 测试定时任务-每十秒执行一次
     * 表达式参考：  https://www.bejson.com/othertools/cron/
     *
     * @author wangjunming
     * @since 2020/5/13 16:22
     */
    @Scheduled(cron = "0/15 * * * * ?")
    public void checkState1() {
//        ReflectionUtils.findMethod();
//        ReflectionUtils.invokeMethod();
        log.info(">>>>> cron测试定时任务-每15秒执行一次检查redis存储信息开始....");
        String key = "nowDateTimes";
        String value = DateUtils.getDateTimes();
        long overdueTime = 60*60*24;
        redisService.setStrKey(key,value,overdueTime);
        final Object strValue = redisService.getStrValue(key);
        log.info("redis中存储的key：{}，value：{}",key,strValue);
        log.info(">>>>> cron测试定时任务-每15秒执行一次检查redis存储信息结束....");
    }

    /**
     * 测试定时任务-每十秒执行一次
     * 表达式参考：  https://www.bejson.com/othertools/cron/
     *
     * @author wangjunming
     * @since 2020/5/13 16:22
     */
//    @Scheduled(cron = "0/25 * * * * ?")
    public void checkState2() {
        log.info(">>>>> cron测试定时任务-每25秒执行一次检查MQ信息开始....");
        RabbitMqUtils.messageDevJson(new RabbitMqQo());
        log.info(">>>>> cron测试定时任务-每25秒执行一次检查MQ信息结束....");
    }

    /**
     * 测试定时任务-每十秒执行一次
     * 表达式参考：  https://www.bejson.com/othertools/cron/
     *
     * @author wangjunming
     * @since 2020/5/13 16:22
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void checkRedisMessage() {
        log.info(">>>>> cron测试定时任务-每15秒执行一次检查MQ信息开始....");
        RabbitMqUtils.messageProdFanout("MQ消息手动确认信息！！");
        log.info(">>>>> cron测试定时任务-每15秒执行一次检查MQ信息结束....");
    }

}
