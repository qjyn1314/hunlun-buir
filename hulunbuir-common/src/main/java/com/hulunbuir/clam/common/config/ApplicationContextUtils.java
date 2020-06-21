package com.hulunbuir.clam.common.config;

import com.hulunbuir.clam.parent.exception.BuirException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * explain: ApplicationContext 工具类
 * </p>
 *
 * @author wangjunming
 * @since 2020/6/21 12:25
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

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
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


}
