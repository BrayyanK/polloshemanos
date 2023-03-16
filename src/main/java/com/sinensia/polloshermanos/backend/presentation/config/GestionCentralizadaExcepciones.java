package com.sinensia.polloshermanos.backend.presentation.config;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GestionCentralizadaExcepciones extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PresentationException.class)
	protected ResponseEntity<?> gestorEspecialparaNullPointerYArithmeticException(PresentationException ex, WebRequest webRequest){
		RespuestaError respuestaError = new RespuestaError(ex.getMessage());
		return handleExceptionInternal(ex, respuestaError, new HttpHeaders(), ex.getHttpStatus(), webRequest);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<?> argumentTypeMismatchExceptionHandler(Exception ex, WebRequest webRequest){
		RespuestaError respuestaError = new RespuestaError(ex.getMessage());
		return handleExceptionInternal(ex, respuestaError, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<?> genericExceptionHandler(Exception ex, WebRequest webRequest){
		RespuestaError respuestaError = new RespuestaError(ex.getMessage());
		return handleExceptionInternal(ex, respuestaError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}
	
}


