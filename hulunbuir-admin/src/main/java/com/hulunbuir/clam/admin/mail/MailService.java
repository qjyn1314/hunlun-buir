package com.hulunbuir.clam.admin.mail;

import com.hulunbuir.clam.admin.test_demo.Person;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
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
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setFrom(fromSender);//邮件发信人
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

    public ByteArrayInputStream getInputStream() {
        // 第一步，创建一个workbook，对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet hssfSheet = workbook.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = hssfSheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
        String[] titles = {"姓名", "年龄"};
        HSSFCell hssfCell = null;
        for (int i = 0; i < titles.length; i++) {
            hssfCell = row.createCell(i);//列索引从0开始
            hssfCell.setCellValue(titles[i]);//列名1
            hssfCell.setCellStyle(hssfCellStyle);//列居中显示
        }
        // 第五步，写入实体数据
        Person person1 = new Person(20, "张三");
        Person person2 = new Person(21, "李四");
        Person person3 = new Person(22, "王五");
        Person person4 = new Person(23, "徐小筱");
        //这里我把list当做数据库啦
        ArrayList<Person> list = new ArrayList<Person>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        for (int i = 0; i < list.size(); i++) {
            row = hssfSheet.createRow(i + 1);
            Person person = list.get(i);
            // 第六步，创建单元格，并设置值
            String name = null;
            if (person.getName() != null) {
                name = person.getName();
            }
            row.createCell(0).setCellValue(name);
            int age = 0;
            if (person.getAge() != 0) {
                age = person.getAge();
            }
            row.createCell(1).setCellValue(age);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
        ByteArrayInputStream iss = null;
        try {
            workbook.write(out);
            workbook.close();
            iss = new ByteArrayInputStream(out.toByteArray());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iss;
    }

}
