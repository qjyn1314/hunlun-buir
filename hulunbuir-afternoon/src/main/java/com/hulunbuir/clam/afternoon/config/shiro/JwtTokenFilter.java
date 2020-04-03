//package com.hulunbuir.clam.afternoon.config.shiro;
//
//import org.apache.shiro.web.filter.authz.AuthorizationFilter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.io.IOException;
//
///**
// * <p>
// * explain: shiro中验证拦截器
// * </p>
// *
// * @author wangjunming
// * @since 2020/3/23 16:52
// */
//public class JwtTokenFilter extends AuthorizationFilter {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
//
//        logger.info("进入验证拦截器。。。。。");
//
//        return super.onAccessDenied(request, response);
//    }
//
//    @Override
//    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
//
//        return true;
//    }
//}