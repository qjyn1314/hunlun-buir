package com.hulunbuir.clam.distributed.admin;

/**
 * <p>
 * Explain:用于发送邮件使用，由于在springAdmin集成中，client端出现了暂不能解决的问题，则将发送邮件放置到admin的server端，使用dubbo进行调用并发送邮件，
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-17 12:52
 */
public interface AdminMailProvider {


    /**
     * 发送文本邮件
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param cc      抄送地址
     */
    void sendSimpleMail(String to, String subject, String content, String... cc);



}
