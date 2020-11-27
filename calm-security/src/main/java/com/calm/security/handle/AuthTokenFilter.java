package com.calm.security.handle;

import com.calm.security.util.AuthUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/21 13:11
 */
@Slf4j
public class AuthTokenFilter extends BasicAuthenticationFilter {

    public AuthTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("过滤器中的请求路径是：{}", request.getRequestURI());
        log.info(request.getHeader(AuthUserUtil.AUTH_TOKEN_KEY));
        chain.doFilter(request, response);
    }
}
