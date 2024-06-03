package com.srirama.palindrome.validation;

/**
 * Custom runtime exception to help with a customised response for validation failure
 */
public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ValidationException(String msg) {
		super(msg);
	}

}
