package com.hulunbuir.clam.route.config.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * <p>
 * explain:通过登录邮箱没有查找到用户信息异常
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/26 13:21
 */
public class NoUserException extends AuthenticationException {
}
