package com.accenture.web.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrintTime {

	private static final Logger logger = Logger.getLogger(PrintTime.class);
	public static final String SERVICE = "execution(* com.accenture.web.service.JosephService.*(..))";
	public static final String BUSINESS = "execution(* com.accenture.web.business.JosephBusiness.*(..))";
	public static final String CONTROLLER = "execution(* com.accenture.web.controller.JosephController.*(..))";
	
	SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Around(SERVICE)
	public Object timeAroundService(ProceedingJoinPoint joinPoint) throws Throwable {

		String startTime = dataFormat.format(new Date());
		logger.info("Service startTime:" + startTime);

		Object object = joinPoint.proceed();

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
		logger.info("Service methodName:" + methodName);

		String endTime = dataFormat.format(new Date());
		logger.info("Service endTime:" + endTime);

		return object;
	}

	@Around(BUSINESS)
	public Object timeAroundBusiness(ProceedingJoinPoint joinPoint) throws Throwable {

		String startTime = dataFormat.format(new Date());
		logger.info("Business startTime:" + startTime);

		Object object = joinPoint.proceed();

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
		logger.info("Business methodName:" + methodName);

		String endTime = dataFormat.format(new Date());
		logger.info("Business endTime:" + endTime);

		return object;
	}

	@Around(CONTROLLER)
	public Object timeAroundController(ProceedingJoinPoint joinPoint) throws Throwable {

		String startTime = dataFormat.format(new Date());
		logger.info("Controller startTime:" + startTime);

		Object object = joinPoint.proceed();

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
		logger.info("Controller methodName:" + methodName);

		String endTime = dataFormat.format(new Date());
		logger.info("Controller endTime:" + endTime);

		return object;
	}

}
