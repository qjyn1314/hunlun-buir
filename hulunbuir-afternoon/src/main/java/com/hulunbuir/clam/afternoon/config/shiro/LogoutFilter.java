//package com.hulunbuir.clam.afternoon.config.shiro;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
///**
// * <p>
// * explain:shiro退出登录拦截器
// * </p>
// *
// * @author wangjunming
// * @since 2020/3/23 16:53
// */
//public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {
//    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//
//        logger.info("---进入退出拦截器----进行退出--当前登录的用户信息是：");
//
//        return super.preHandle(request, response);
//    }
//}