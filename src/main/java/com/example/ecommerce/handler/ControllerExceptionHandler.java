package com.example.ecommerce.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.CMResponseDTO;
import com.example.ecommerce.handler.ex.CustomValidationException;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public CMResponseDTO<Map<String, String>> validationException(CustomValidationException e) {
		return new CMResponseDTO(-1 ,e.getMessage(), e.getErrorMap());
	}
}
