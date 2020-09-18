package com.calm.security;

import com.hulunbuir.clam.common.config.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/15 17:57
 */
@DependsOn("applicationContextUtils")
@Component
public class AuthService implements Auth {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private HttpServletRequest request;

    public static AuthService me() {
        return ApplicationContextUtils.getBean(AuthService.class);
    }

    public String handleView(){
        return request.getRequestURI().replace(HTML_SUFFIX, "");
    }





}
