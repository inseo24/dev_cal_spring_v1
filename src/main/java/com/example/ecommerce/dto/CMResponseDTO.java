package com.example.ecommerce.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CMResponseDTO<T> {

	private int code; 
	private String message;
	private T data;

}