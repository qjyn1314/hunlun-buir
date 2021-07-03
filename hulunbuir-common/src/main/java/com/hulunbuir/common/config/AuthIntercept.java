package com.hulunbuir.common.config;

import jodd.util.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * explain:
 * 拦截，方法执行顺序：（由上至下）
 * HandlerExecutionChain.applyPreHandle()
 * MyInterceptor.preHandle()
 * ViewController.find()
 * HandlerExecutionChain.applyPostHandle()
 * MyInterceptor.postHandle()
 * HandlerExecutionChain.triggerAfterCompletion()
 * MyInterceptor.afterCompletion()
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/31 21:59
 */
@Slf4j
@Component
@Configuration
public class AuthIntercept implements HandlerInterceptor {
    private final List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501);
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        // 跨域的概念： https://blog.csdn.net/weixin_36380516/article/details/116382640
        // 跨域配置参考： https://blog.csdn.net/iteye_19045/article/details/108386177
        // http://www.ruanyifeng.com/blog/2016/04/cors.html
        // https://blog.csdn.net/weixin_43841924/article/details/111614936
        // https://www.cnblogs.com/diandianquanquan/p/10607102.html
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //配置默认错误页面
        if (errorCodeList.contains(response.getStatus())) {
            response.sendRedirect("/error/404");
            return false;
        }
        final String uri = request.getRequestURI();
        log.info("请求的路径是：{}", uri);
        final String interceptUrl = BuirProperties.me().getInterceptUrl();
//        log.info("配置请求放过的路径是：{}", interceptUrl);
        String[] anonUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(interceptUrl, StringPool.COMMA);
        return true;
        /*
        boolean flag= false;
        for (String url : anonUrl) {
            if (pathMatcher.match(url, uri)){
                flag = true;
            }
        }
        return flag;
        */
    }

}
