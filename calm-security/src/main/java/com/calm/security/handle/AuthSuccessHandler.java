package com.calm.security.handle;

import com.calm.security.support.Auth;
import com.calm.security.util.AuthUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * explain:登陆成功处理器
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/21 10:33
 */
@Slf4j
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("登录成功了---------进入登陆成功处理器！！！");
        final String authToken = AuthUserUtil.getAuthToken(response,authentication);
        log.info("当前登录用户生成的token是：{}",authToken);
        response.sendRedirect(Auth.LOGIN_SUCCESS_URL);
    }
}
