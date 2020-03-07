package com.hulunbuir.clam.admin.consumer;

import com.alibaba.dubbo.config.annotation.Service;
import com.hulunbuir.clam.admin.mail.MailService;
import com.hulunbuir.clam.distributed.admin.AdminMailProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Explain:发送邮件的具体实现其他服务通过调用dubbo接口来进行发送邮件
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-17 12:53
 */
@Service(version = "1.0.0",timeout = 10000,interfaceClass = AdminMailProvider.class)
@Component
@Slf4j
public class AdminMailProviderImpl implements AdminMailProvider {

    @Autowired
    private MailService mailService;

    /**
     * 发送文本邮件
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param cc      抄送地址
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content, String... cc) {
        mailService.sendSimpleMail(to, subject, content, cc);
    }
}
