package com.hulunbuir.clam.admin.mqconfig;

import java.io.Serializable;

/**
 * <p>
 * explain:用于MQ中的消息传输使用，传入对象的json格式，在接收的时候需要进行解析
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/21 10:14
 */
public final class RabbitMqQo implements Serializable {

    private String userName;
    private String userMail;
    private String userAge;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "RabbitMqQo{" +
                "userName='" + userName + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userAge='" + userAge + '\'' +
                '}';
    }
}
