package com.example.ecommerce.handler.ex;

import java.util.Map;


public class CustomValidationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Map<String, String> errorMap;
	
	public CustomValidationException(String message, Map<String, String> errorMap) {
		this.message = message;
		this.errorMap = errorMap;
	}
	
	public Map<String, String> getErrorMap(){
		return errorMap;
	}
}
