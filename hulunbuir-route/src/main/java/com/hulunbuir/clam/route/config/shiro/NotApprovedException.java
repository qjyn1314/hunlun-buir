package com.hulunbuir.clam.route.config.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * <p>
 * explain: 用户状态的异常类
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/16 12:46
 */
public class NotApprovedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;
    private Integer status;

    public NotApprovedException() {
        super();
    }

    public NotApprovedException(Integer status) {
        this(getStatusMessage(status));
    }

    public NotApprovedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotApprovedException(String message) {
        super(message);
    }

    public NotApprovedException(Throwable cause) {
        super(cause);
    }

    private static String getStatusMessage(Integer status) {
        String statusContent = Integer.valueOf("0").equals(status) ? "未审核" : "已冻结";
        return "账号" + statusContent + "，请联系管理员，邮箱地址：qjyn1314@163.com";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
