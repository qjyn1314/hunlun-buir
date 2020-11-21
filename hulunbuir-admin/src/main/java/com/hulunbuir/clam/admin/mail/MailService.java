package com.hulunbuir.clam.admin.mail;

import com.hulunbuir.clam.common.config.BuirProperties;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

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
        message.setFrom(BuirProperties.me().getMailSender());
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

    /**
     * 发送文本邮件并携带附件
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param cc      抄送地址
     */
    public void sendSimpleMailAndFile(String to, String subject, String content, String... cc) throws Exception {
        log.info("收件人：{}，邮件主题：{}，邮件内容：{}，抄送地址：{}", to, subject, content, cc);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setFrom(BuirProperties.me().getMailSender());//邮件发信人
        messageHelper.setTo(to);//邮件收信人
        messageHelper.setSubject(subject);//邮件主题
        messageHelper.setText(content);//邮件内容
        messageHelper.setCc(cc);//抄送
        messageHelper.setSentDate(new Date());//发送时间
        String emailFileName = "测试excel发送-" + DateUtils.getDateTimes() + ".xlsx";
        DataSource dataSource = new FileDataSource(new File(""));
        messageHelper.addAttachment(emailFileName, dataSource);//添加附件信息
        mailSender.send(messageHelper.getMimeMessage());//正式发送邮件
    }

}
