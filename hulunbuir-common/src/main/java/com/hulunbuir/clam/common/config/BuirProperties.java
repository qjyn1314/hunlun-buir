package com.hulunbuir.clam.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * <p>
 * explain: 公共的配置变量
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/27 10:26
 */
@DependsOn("applicationContextUtils")
@Configuration
@ConfigurationProperties(prefix = "buir")
public class BuirProperties {

    private static final String SERVER_PORT = "server.port";
    @Autowired
    private Environment environment;
    private static Environment env;
    private String interceptUrl;
    private String validationUrl;
    private String mailSender;

    public static BuirProperties me() {
        return ApplicationContextUtils.getBean(BuirProperties.class);
    }

    @PostConstruct
    public void init() {
        env = environment;
    }

    public static String getPort() {
        return env.getProperty(SERVER_PORT);
    }

    public String getInterceptUrl() {
        return interceptUrl;
    }

    public void setInterceptUrl(String interceptUrl) {
        this.interceptUrl = interceptUrl;
    }

    public String getValidationUrl() {
        return validationUrl;
    }

    public void setValidationUrl(String validationUrl) {
        this.validationUrl = validationUrl;
    }

    public String getMailSender() {
        return mailSender;
    }

    public void setMailSender(String mailSender) {
        this.mailSender = mailSender;
    }
}
