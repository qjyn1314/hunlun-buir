package com.calm.security;

import org.springframework.security.core.AuthenticationException;

/**
 * <p>
 * explain: 用户账号未激活异常
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/21 12:19
 */
public class NotActivationException extends AuthenticationException {
    public NotActivationException(String msg) {
        super(msg);
    }
    public NotActivationException(String msg, Throwable t) {
        super(msg, t);
    }
}
