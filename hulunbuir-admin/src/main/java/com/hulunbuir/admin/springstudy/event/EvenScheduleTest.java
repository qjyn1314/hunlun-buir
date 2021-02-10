package com.hulunbuir.admin.springstudy.event;

import com.hulunbuir.admin.springstudy.event.annotationsevent.SendMailAnno;
import com.hulunbuir.admin.springstudy.event.annotationsevent.UserLoginAnnoListener;
import com.hulunbuir.admin.springstudy.event.interfaceevent.SendMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * explain: spring事件的测试类
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/10 20:33
 */
@Slf4j
@Component
public class EvenScheduleTest {

    @Autowired
    private ApplicationEventMulticaster simpleEventMulticaster;

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    /**
     * 同步的事件发送，基于接口实现
     * @author wangjunming
     * @since 2020/12/10 21:34
     */
//    @Scheduled(cron = "0/10 * * * * ?")
    public void sendMailEventByInterface() {
        log.info(">>>>> cron测试定时任务-基于接口-每10秒执行一次发送邮件事件开始....");
        final String localDateFormat = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        String username = "zhangsan";
        String email = "qjyn1314@163.com";
        SendMail sendMail = new SendMail(simpleEventMulticaster, username + localDateFormat, email);
        simpleEventMulticaster.multicastEvent(sendMail);
        log.info(">>>>> cron测试定时任务-基于接口-每10秒执行一次发送邮件事件结束....");
    }

    @Autowired
    private UserLoginAnnoListener userLoginAnnoListener;

    /**
     * 同步的事件发送，基于注解实现
     * @author wangjunming
     * @since 2020/12/10 21:34
     */
//    @Scheduled(cron = "0/10 * * * * ?")
    public void sendMailEventByAnnotations() {
        log.info(">>>>> cron测试定时任务-基于注解-每10秒执行一次发送邮件事件开始....");
        final String localDateFormat = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        String username = "zhangsan";
        String email = "qjyn1314@163.com";
        SendMailAnno sendMail = new SendMailAnno("注解形式的发送", username + localDateFormat, email);
        userLoginAnnoListener.sendMail(sendMail);
        log.info(">>>>> cron测试定时任务-基于注解-每10秒执行一次发送邮件事件结束....");
    }

}
