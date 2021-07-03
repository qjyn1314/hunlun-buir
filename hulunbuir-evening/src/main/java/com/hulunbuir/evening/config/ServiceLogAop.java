package com.zhichubao.project.web.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 
 * @ClassName: ServiceLogAop
 * @Description: Record core business access, results, time consuming, etc.
 * @author Nirvana
 * @date 2017年7月5日 下午12:07:31
 *
 */

@Aspect
@Order(5)
@Component
public class ServiceLogAop {

	private Logger logger = LoggerFactory.getLogger(ServiceLogAop.class);

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut("execution(public * com.zhichubao.project.api..*.*(..)) || execution(public * com.zhichubao.demand.api..*.*(..))")
	public void serviceLog() {
	}

	@Before("serviceLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		// 记录下请求内容
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

	}

	@AfterReturning(returning = "ret", pointcut = "serviceLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("RESPONSE : " + ret);
		logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
	}
}
