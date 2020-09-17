package com.hulunbuir.clam.parent.submit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.hulunbuir.clam.parent.result.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Explain:表单防重复提交Aop
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-09 17:33
 */
@Aspect
@Component
public class NoRepeatSubmitAop {
    private final Logger logger = LoggerFactory.getLogger(NoRepeatSubmitAop.class);
    @Autowired
    private Cache<String, Integer> cache;
    @Around("execution(* com.hulunbuir.clam.*..*(..)) && @annotation(nrs)")
    public Object arround(ProceedingJoinPoint pjp, NoRepeatSubmit nrs) {
        try {
            logger.info("进入防重复提交的aop");
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String sessionId = Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getSessionId();
            assert attributes != null;
            HttpServletRequest request = attributes.getRequest();
            String key = sessionId + "-" + request.getServletPath();
            logger.info("key：{}",key);
            if (cache.getIfPresent(key) == null) {// 如果缓存中有这个url视为重复提交
                Object o = pjp.proceed();
                cache.put(key, 0);
                return o;
            } else {
                logger.error("重复提交");
                return JsonResult.error("请勿重复提交!");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            logger.error("验证重复提交时出现未知异常!");
            return JsonResult.error("请勿重复提交!!!");
        }

    }
    @Bean
    public Cache<String, Integer> getCache() {
        // 缓存有效期为20秒
        return CacheBuilder.newBuilder().expireAfterWrite(20L, TimeUnit.SECONDS).build();
    }

}
