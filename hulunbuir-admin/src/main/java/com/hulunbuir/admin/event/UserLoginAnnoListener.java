package com.hulunbuir.admin.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 * explain: 配置监听器
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/10 21:58
 */
@Slf4j
@Component
public class UserLoginAnnoListener {

    @EventListener
    public void sendMail(SendMailAnno event) {
        log.info("登陆成功之后发送邮件，发送成功的时间是：{}，用户是：{}，邮箱是：{}", LocalDateTime.now(),event.getUsername(),event.getEmail());
    }

}
