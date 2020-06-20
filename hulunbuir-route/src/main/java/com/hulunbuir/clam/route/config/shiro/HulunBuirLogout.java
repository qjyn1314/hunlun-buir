//package com.hulunbuir.clam.route.config.shiro;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.web.filter.authc.LogoutFilter;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
///**
// * <p>
// * explain:
// * </p>
// *
// * @author wangjunming
// * @since 2020/6/20 13:53
// */
//@Slf4j
//@Component
//public class HulunBuirLogout extends LogoutFilter {
//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        log.info("---进入退出拦截器----进行退出--当前登录的用户信息是：");

//        String redirectUrl = "/login";
//        super.setRedirectUrl(redirectUrl);
//        return super.preHandle(request,response);
//    }
//}
