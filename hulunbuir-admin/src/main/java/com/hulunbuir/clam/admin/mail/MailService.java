package com.hulunbuir.clam.admin.mail;

import com.hulunbuir.clam.parent.exception.HulunBuirException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-12 16:36
 */
@Component
@Slf4j
public class MailService {

    /**
     * 发送者
     *
     * @author wangjunming
     * @param null:
     * @return: null
     * @since 2020/2/17 13:04
     */
    @Value("${hulun-buit.mail.sender}")
    private String fromSender;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送文本邮件
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param cc      抄送地址
     */
    public void sendSimpleMail(String to, String subject, String content, String... cc) throws Exception {
        log.info("收件人：{}，邮件主题：{}，邮件内容：{}，抄送地址：{}", to, subject, content, cc);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromSender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error("发送文本邮件失败!!!", e);
            throw HulunBuirException.build("发送文本邮件失败!!!");
        }
    }


}
