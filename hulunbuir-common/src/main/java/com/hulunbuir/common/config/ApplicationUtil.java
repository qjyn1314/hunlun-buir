package com.hulunbuir.common.config;

import com.hulunbuir.parent.exception.BuirException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * <p>
 * explain: ApplicationContext 工具类
 * </p>
 *
 * @author wangjunming
 * @since 2020/6/21 12:25
 */
@Configuration
public class ApplicationUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return context.getBean(clazz);
    }

    public static <T> T getBeanByClass(String beanName, Class<T> clazz) {
        checkApplicationContext();
        return context.getBean(beanName, clazz);
    }

    private static void checkApplicationContext() {
        if (context == null) {
            BuirException.build("没有注入ApplicationContext！！");
        }
    }

    /**
     * 获取 HttpServletRequest
     *
     * @author wangjunming
     * @since 2020/6/21 12:37
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取request中的sessionId
     *
     * @author wangjunming
     * @since 2020/7/8 10:27
     */
    public static String getRequestSessionId() {
        final HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return httpServletRequest.getSession().getId();
    }


}
