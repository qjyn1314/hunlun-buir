package com.hulunbuir.clam.route.config.jwt;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class RouteIntercept implements HandlerInterceptor {

    @Autowired
    private AfternoonConfig afternoonConfig;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        final String requestURI = request.getRequestURI();
        final String interceptUrl = afternoonConfig.getInterceptUrl();
        String[] anonUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(interceptUrl, StringPool.COMMA);
//        log.info("anonUrl::{}", Arrays.asList(anonUrl));
//        log.info("requestURI：：{}",requestURI);
//        log.info("interceptUrl：：{}",interceptUrl);
//        log.info("进入 preHandle 方法...路径：{}", requestURI);
        /*  String[] anonUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(authentication, StringPool.COMMA);
        boolean flag= false;
        for (String url : anonUrl) {
            if (pathMatcher.match(url, requestURI)){
                flag = true;
            }
        }
        return flag;*/
        return true;
    }

}
