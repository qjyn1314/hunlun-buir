package com.hulunbuir.admin.design.proxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 2019/1/9
 * 创建人：Wjunming
 */
@Aspect
@Component
public class LoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 定义切入点，切入点为com.example.aop下的所有函数com.example.jack.controller
     *
     */
    @Pointcut("execution(public * com.hulunbuir.admin.mail.*.*(..))")
    public void webLog(){
    }

    @Before("webLog()")
    public void before(JoinPoint joinPoint){
        //获取方法名
        String name = joinPoint.getSignature().getName();
        //获取参数列表
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("进入的方法名：--"+name+"的参数:--"+args);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL：--"+request.getRequestURI());
        logger.info("HTTP_METHOD:--"+request.getMethod());
        logger.info("IP：--"+request.getRemoteAddr());
        Enumeration<String> parameter = request.getParameterNames();
        while (parameter.hasMoreElements()){
            String element = parameter.nextElement();
            logger.info("key:{"+element+"},value:{"+request.getParameter(element)+"}");
        }
    }


    @After("webLog()")
    public void after(JoinPoint joinPoint){
        //获取方法名
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法结束");
    }

    @AfterReturning(returning="result",pointcut = "webLog()")
    public void afterReturn(JoinPoint joinPoint, Object result){
        //获取方法名
        String name = joinPoint.getSignature().getName();
        logger.info(name+"方法的结果是"+result);
        //获取参数列表
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("进入的方法名：--"+name+"的参数:--"+args);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL：--"+request.getRequestURI());
        logger.info("HTTP_METHOD:--"+request.getMethod());
        logger.info("IP：--"+request.getRemoteAddr());
        Enumeration<String> parameter = request.getParameterNames();
        while (parameter.hasMoreElements()){
            String element = parameter.nextElement();
            logger.info("key:{"+element+"},value:{"+request.getParameter(element)+"}");
        }
        logger.info("执行过后返回的结果是：--{"+result+"}");
    }

    @AfterThrowing(value="webLog()",throwing="ex",pointcut = "webLog()")
    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        //获取方法名
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法抛出异常："+ex);
    }
}
