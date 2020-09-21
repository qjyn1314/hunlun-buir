package com.calm.security.handle;

import com.calm.security.AuthUserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * explain: 退出登录处理器
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/21 12:26
 */
@Component
public class AuthLogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        AuthUserUtil.logoutHandle(request, response, authentication);
    }
}
