package com.collabera.jump.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.collabera.jump.exception.ExceptionResponse;

@ControllerAdvice
public class EmsExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handle(Exception e) {
		ExceptionResponse response = new ExceptionResponse(
				"Sorry, something went wrong :(", e.getMessage());
		return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ExceptionResponse> handle(RuntimeException e) {
		ExceptionResponse response = new ExceptionResponse(
				"Sorry, something went wrong :(", e.getMessage());
		return ResponseEntity.badRequest().body(response);
	}

}
