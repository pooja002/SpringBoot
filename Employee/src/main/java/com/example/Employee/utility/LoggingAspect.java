package com.example.Employee.utility;

import java.text.DateFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Aspect
public class LoggingAspect {
	
	
	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	@After("execution(* com.example.Employee.Service.EmployeeServiceImpl.getAllEmployees(..))")
	public void logAfterAdvice(JoinPoint joinPoint) {
		logger.info("In After  Advice, Joinpoint signature :{}", joinPoint.getSignature());
		long time = System.currentTimeMillis();
		String date = DateFormat.getDateTimeInstance().format(time);
		logger.info("Report generated at time {}", date);
	
	}
}
