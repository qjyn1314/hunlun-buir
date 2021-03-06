package com.hulunbuir.common.mail;

/**
 * <p>
 * Explain:邮件主题，邮件内容  枚举类
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-12 16:52
 */
public enum MailConstants {

    ZHU_CE("注册", "欢迎<%s>注册《呼伦贝尔大草原》___>www.hulunbuir.vip。"),

    VERIFICATION("注册验证码", "欢迎注册《呼伦贝尔大草原》,您的注册验证码是：%s，有效期是5分钟"),

    ;

    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 文件内容
     */
    private String content;

    MailConstants(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
