package com.hulunbuir.clam.route.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain: 自定义工程配置
 * </p>
 *
 * @author wangjunming
 * @since 2020/6/27 19:59
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "buir.config")
public class AfternoonConfig {

    private String interceptUrl;

    private String validationUrl;


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
}
