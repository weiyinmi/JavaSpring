package com.accenture.web.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.accenture.web.service.JosephService;
import com.accenture.web.service.impl.JosephServiceImpl;

@Aspect
@Component
public class PrintTime {

	private static final Logger logger = Logger.getLogger(PrintTime.class);
	public static final String POINT = "execution(* com.accenture.web.service.JosephService.josephFunction(..))";

	@Around("execution(* com.accenture.web.service.impl.JosephServiceImpl.*.*(..))")
	public void timeAround(ProceedingJoinPoint joinPoint) throws Throwable {

		long startTime = System.currentTimeMillis();
		logger.info("startTime:" + startTime);
		
		Object object = joinPoint.proceed(joinPoint.getArgs());

		// Get method name
		long endTime = System.currentTimeMillis();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

		logger.info("methodName:" + methodName);
		logger.info("endTime:" + endTime);

	}

}
