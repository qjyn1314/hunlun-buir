package com.hulunbuir.clam.route.config.shiro;

import com.hulunbuir.clam.distributed.model.UserManager;
import com.hulunbuir.clam.route.config.jwt.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * explain: 自定义退出登录拦截器
 * </p>
 *
 * @author wangjunming
 * @since 2020/6/20 13:53
 */
@Slf4j
public class HulunBuirLogout extends LogoutFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        final UserManager userManager = ShiroTool.currentUser();
        log.info("---进入退出拦截器----进行退出--当前登录的用户信息是：{}",userManager);
        CurrentUser.deleteUserMessageCookie((HttpServletRequest)request,(HttpServletResponse)response);
        return super.preHandle(request,response);
    }
}
