package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;


@RestControllerAdvice
public class CustomExceptionHandler {
	/**
	 * Helps in custom handling for the validation exception
	 * @param e
	 * @return
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WebExchangeBindException.class)
	public String validationException(ValidationException e) {
		return e.getMessage();
	}

	/**
	 * Helps in custom handling for all other runtime exceptions
	 * @param e
	 * @return
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {

		return e.getMessage();
	}

}
