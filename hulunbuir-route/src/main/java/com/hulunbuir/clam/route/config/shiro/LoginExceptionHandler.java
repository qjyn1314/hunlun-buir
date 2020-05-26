package com.hulunbuir.clam.route.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/26 14:00
 */
@Slf4j
@ControllerAdvice
public class LoginExceptionHandler {

    /**
     * 统一处理请求的方法不正确异常
     *
     * @author wangjunming
     * @since 2020/2/12 18:05
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception1) {
        log.error("统一处理请求的方法不正确异常-异常", exception1);
        if (ShiroTool.isAuthenticated()) {
            return "redirect:/console.html";
        }
        return "/error/404";
    }

}
