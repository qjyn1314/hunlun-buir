package com.hulunbuir.clam.evening.auth;

import com.hulunbuir.clam.common.config.ApplicationContextUtils;
import com.hulunbuir.clam.common.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * explain:获取用户信息
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/26 17:24
 */
@DependsOn("applicationContextUtils")
@Component
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public static AuthService me() {
        return ApplicationContextUtils.getBean(AuthService.class);
    }

    public boolean isLogin(HttpServletRequest request) {
        final String tokenByHeader = RequestUtils.getTokenByHeader(request);

        return false;
    }










}