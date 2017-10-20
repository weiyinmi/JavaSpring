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
	public static final String SERVICE = "execution(* com.accenture.web.service.impl.JosephServiceImpl.*.*(..))";
	public static final String BUSINESS = "execution(* com.accenture.web.bussiness.impl.JosephBusinessImpl.*.*(..))";
	public static final String CONTROLLER = "execution(* com.accenture.web.service.JosephController.*.*(..))";

	@Around(SERVICE)
	public Object timeAroundService(ProceedingJoinPoint joinPoint) throws Throwable {

		long startTime = System.currentTimeMillis();
		logger.info("startTime:" + startTime);
		
		Object object = joinPoint.proceed();

		long endTime = System.currentTimeMillis();
		logger.info("endTime:" + endTime);
		
		return object;
	}
	
	@Around(BUSINESS)
	public Object timeAroundBusiness(ProceedingJoinPoint joinPoint) throws Throwable {

		long startTime = System.currentTimeMillis();
		logger.info("startTime:" + startTime);
		
		Object object = joinPoint.proceed();

		long endTime = System.currentTimeMillis();
		logger.info("endTime:" + endTime);
		
		return object;
	}
	
	@Around(CONTROLLER)
	public void timeAroundController(ProceedingJoinPoint joinPoint) throws Throwable {

		long startTime = System.currentTimeMillis();
		logger.info("startTime:" + startTime);
		
		Object object = joinPoint.proceed();

		long endTime = System.currentTimeMillis();
		logger.info("endTime:" + endTime);

	}

}
