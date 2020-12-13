package com.hulunbuir.clam.admin.event;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * explain: 自定义发送邮件的事件
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/10 13:27
 */
public class SendMailAnno extends ApplicationEvent {

    private String username;
    private String email;

    /**
     * 构造方法
     *
     * @author wangjunming
     * @since 2020/12/10 20:37
     */
    public SendMailAnno(Object source) {
        super(source);
    }
    /**
     * 构造方法-2
     * @author wangjunming
     * @since 2020/12/10 20:37
     */
    public SendMailAnno(Object source, String username, String email) {
        super(source);
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
