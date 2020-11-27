package com.hulunbuir.clam.admin.threadconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/11/22 21:17
 */
@Slf4j
@Component
public class ThreadTest {

    @Autowired
    private ThreadService service;

//    @Scheduled(cron = "0/10 * * * * ?")
    public void threadServiceSayHello() {
        log.info(">>>>> cron测试定时任务-每10秒执行一次检查线程池信息开始....");
        service.sayHello("ThreadService");
        log.info(">>>>> cron测试定时任务-每10秒执行一次检查线程池信息结束....");
    }


}
