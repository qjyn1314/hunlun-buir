package com.hulunbuir.admin.submit;

import com.hulunbuir.parent.result.JsonResult;
import com.hulunbuir.common.config.ApplicationUtil;
import com.hulunbuir.common.config.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Explain:表单防重复提交Aop
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-09 17:33
 */
@Slf4j
@Aspect
@Component
public class NoRepeatSubmitAop {

    /**20秒内重复提交此接口，即视为重复提交*/
    private final static int TIMEOUT = 20;
    @Autowired
    private RedisService redisService;

    @Around("execution(* com.hulunbuir.admin.controller.AdminController.*(..)) && @annotation(noRepeatSubmit)")
    public Object arround(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) {
        try {
            log.info("进入防重复提交的aop");
            HttpServletRequest request = ApplicationUtil.getHttpServletRequest();
            String sessionId = ApplicationUtil.getRequestSessionId();
            String key = sessionId + "_" + request.getServletPath();
            log.info("key：{}", key);
            // 如果缓存中有这个url视为重复提交
            if (redisService.validationStrValue(key)) {
                log.error("重复提交");
                return JsonResult.error("请勿重复提交!");
            } else {
                Object o = pjp.proceed();
                redisService.setStrKey(key, key, TIMEOUT);
                return o;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            log.error("验证重复提交时出现未知异常!");
            return JsonResult.error("请勿重复提交!!!");
        }
    }

}
