package com.hulunbuir.admin.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 * explain: 自定义发送邮件的监听器
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/10 21:08
 */
@Slf4j
@Component
public class SendMailListener implements ApplicationListener<SendMail> {

    @Override
    public void onApplicationEvent(SendMail sendMail) {
        log.info("登陆成功之后发送邮件，发送成功的时间是：{}", LocalDateTime.now());
    }
}
