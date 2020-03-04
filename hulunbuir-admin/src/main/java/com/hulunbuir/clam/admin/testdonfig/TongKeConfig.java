package com.hulunbuir.clam.admin.testdonfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-03-02 9:54
 */
@Component
@ConfigurationProperties(prefix = "jd.tongke")
public class TongKeConfig {

    /*宙斯第三方应用的appKey*/
    private String appKey = "asdasdasdasdad";
    /*开发者提交的地址*/
    private String isvProUrl = "";
    /*授权过后的回调地址*/
    private String redirectUri = "";

}
