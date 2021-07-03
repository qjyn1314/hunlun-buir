package com.hulunbuir.evening.config;

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

@Aspect
@Order(5)
@Component
public class ServiceLogAop {

	private final Logger logger = LoggerFactory.getLogger(ServiceLogAop.class);

	private final static ThreadLocal<Long> START_TIME = new ThreadLocal<>();

	@Pointcut("execution(public * com.hulunbuir.evening.generationcode.service..*.*(..)) || execution(public * com.hulunbuir.evening.persistence.service..*.*(..))")
	public void serviceLog() {
	}

	@Before("serviceLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		START_TIME.set(System.currentTimeMillis());
		// 记录下请求内容
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

	}

	@AfterReturning(returning = "ret", pointcut = "serviceLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("RESPONSE : " + ret);
		logger.info("SPEND TIME : " + (System.currentTimeMillis() - START_TIME.get()));
		START_TIME.remove();
	}
}
