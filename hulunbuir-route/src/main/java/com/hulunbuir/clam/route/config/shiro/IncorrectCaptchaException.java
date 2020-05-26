package com.hulunbuir.clam.route.config.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码错误异常
 *
 * @author wangjunming
 * @since 2020/5/24 19:17
 */
public class IncorrectCaptchaException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }

}
