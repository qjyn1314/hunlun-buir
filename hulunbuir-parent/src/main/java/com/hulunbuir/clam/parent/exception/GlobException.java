package com.hulunbuir.clam.parent.exception;

import com.hulunbuir.clam.parent.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.NestedServletException;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Explain:全局异常处理
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-12 17:54
 */
@ControllerAdvice
@Slf4j
public class GlobException {

    /**
     * 系统异常
     *
     * @param e:
     * @return com.hulunbuir.clam.parent.result.JsonResult
     * @author wangjunming
     * @since 2020/2/12 18:03
     */
    @ResponseBody
    @ExceptionHandler(value = HulunBuirException.class)
    public JsonResult handleHulunBuirException(HulunBuirException e) {
        log.error("系统错误", e);
        return JsonResult.error(e.getMessage());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e:
     * @return com.hulunbuir.clam.parent.result.JsonResult
     * @author wangjunming
     * @since 2020/2/12 18:03
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public JsonResult validExceptionHandler(BindException e) {
        log.error("实体对象传参-异常", e);
        String defaultMessage = new String();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        FieldError error = fieldErrors.get(0);
        defaultMessage = error.getDefaultMessage();
        return JsonResult.error(defaultMessage);
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e:
     * @return com.hulunbuir.clam.parent.result.JsonResult
     * @author wangjunming
     * @since 2020/2/12 18:05
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public JsonResult handleConstraintViolationException(ConstraintViolationException e) {
        log.error("普通传参-异常", e);
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return JsonResult.error(message.toString());
    }

    /**
     * 统一处理请求的方法不正确异常
     *
     * @author wangjunming
     * @since 2020/2/12 18:05
     */
    @ExceptionHandler(value = RequestRejectedException.class)
    public String handleHttpRequestMethodNotSupportedException(RequestRejectedException e) {
        log.error("统一处理请求的方法不正确异常-异常", e);
        return "/error/404";
    }

    /**
     * 统一处理请求的方法不正确异常
     *
     * @author wangjunming
     * @since 2020/2/12 18:05
     */
    @ExceptionHandler(value = TemplateInputException.class)
    public String handleTemplateInputException(TemplateInputException e) {
        log.error("统一处理请求的方法不正确异常-异常", e);
        return "/error/404";
    }

    /**
     * 统一处理请求的方法不正确异常
     *
     * @author wangjunming
     * @since 2020/2/12 18:05
     */
    @ExceptionHandler(value = NestedServletException.class)
    public String handleNestedServletException(NestedServletException e) {
        log.error("统一处理请求的方法不正确异常-异常", e);
        return "/error/404";
    }

}
