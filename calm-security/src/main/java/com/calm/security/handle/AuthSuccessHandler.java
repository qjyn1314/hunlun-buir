package com.calm.security.handle;

import com.calm.security.AuthUserUtil;
import com.calm.security.CurrentUser;
import com.hulunbuir.clam.parent.tool.RequestUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * explain:登陆成功处理器
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/21 10:33
 */
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final CurrentUser currentUser = AuthUserUtil.successUser(authentication);
        final String authToken = AuthUserUtil.getAuthToken(currentUser);
        RequestUtils.setTokenByHeader(response,AuthUserUtil.AUTH_TOKEN_KEY,authToken);
        RequestUtils.setCookie(response,AuthUserUtil.AUTH_TOKEN_KEY,authToken,AuthUserUtil.AUTH_COOKIE_TIME);
    }
}
