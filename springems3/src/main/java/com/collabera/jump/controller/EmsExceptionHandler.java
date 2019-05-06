package com.collabera.jump.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.collabera.jump.exception.ExceptionResponse;

@ControllerAdvice
public class EmsExceptionHandler {
	
	private Logger logger = LogManager.getLogger(EmsExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handle(Exception e) {
		logger.error(e.getMessage());
		e.printStackTrace();
		
		ExceptionResponse response = new ExceptionResponse(
				"Sorry, something went wrong :(", e.getMessage());
		return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ExceptionResponse> handle(RuntimeException e) {
		e.printStackTrace();
		ExceptionResponse response = new ExceptionResponse(
				"Sorry, something went wrong :(", e.getMessage());
		return ResponseEntity.badRequest().body(response);
	}

}
