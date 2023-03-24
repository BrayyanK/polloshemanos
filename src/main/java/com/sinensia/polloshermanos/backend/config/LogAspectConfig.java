package com.sinensia.polloshermanos.backend.config;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LogAspectConfig {

	private Logger logger = LoggerFactory.getLogger(LogAspectConfig.class);
	
	@Before(value="execution(* com.sinensia.polloshermanos.backend.presentation.controllers.*.*(..))")
	public void logPresentationLayer(JoinPoint joinPoint) {
		crearLog("Presentation Layer", joinPoint);
	}
	
	@Before(value="execution(* com.sinensia.polloshermanos.backend.business.services.impl.*.*(..))")
	public void logBusinessLayer(JoinPoint joinPoint) {
		crearLog("Business Layer", joinPoint);
	}
	
	// *************************************************************************
	//
	// PRIVATE METHODS
	//
	// *************************************************************************
	
	private void crearLog(String capa, JoinPoint joinPoint) {
		
		String argumentos = Arrays.toString(joinPoint.getArgs());
		String clase = joinPoint.getTarget().getClass().getSimpleName();
		String metodo = joinPoint.getSignature().getName();
		
		logger.info("{}: {}.{}()", capa, clase, metodo, argumentos);
	}
	
}
