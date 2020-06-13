package com.hulunbuir.clam.route.config.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * explain:使拦截器生效
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/31 22:06
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    private final RouteIntercept routeIntercept;

    public WebAppConfigurer(RouteIntercept routeIntercept) {
        this.routeIntercept = routeIntercept;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个拦截器
        registry.addInterceptor(routeIntercept).addPathPatterns("/**");

    }
}
