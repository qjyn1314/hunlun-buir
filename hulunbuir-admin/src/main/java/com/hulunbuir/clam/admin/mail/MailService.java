package com.hulunbuir.clam.admin.mail;

import com.hulunbuir.clam.parent.exception.HulunBuirException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(fromSender);//邮件发信人
        messageHelper.setTo(to);//邮件收信人
        messageHelper.setSubject(subject);//邮件主题
        messageHelper.setText(content);//邮件内容
        messageHelper.setCc(cc);//抄送

        String fileURL = "http://cyjf.oss-cn-beijing.aliyuncs.com/fenxiangyirenzheng.png";
        String fileName = "fenxiangyirenzheng.png";
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = httpConn.getInputStream();
//        ByteArrayOutputStream byteArrayOutputStreamc = new OutputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream(1000);
        messageHelper.setSentDate(new Date());
        InputStreamSource iss = new ByteArrayResource(os.toByteArray());

        messageHelper.addAttachment(MimeUtility.decodeText(fileName), iss);

        mailSender.send(messageHelper.getMimeMessage());//正式发送邮件
    }


}
