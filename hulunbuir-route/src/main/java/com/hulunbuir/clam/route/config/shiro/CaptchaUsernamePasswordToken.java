package com.hulunbuir.clam.route.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 拓展登陆验证字段
 *
 * @author wangjunming
 * @since 2020/5/24 19:17
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    //验证码字符串
    private String captcha;

    public CaptchaUsernamePasswordToken(String username, char[] password,
                                        boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
