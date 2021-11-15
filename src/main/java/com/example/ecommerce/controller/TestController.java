package com.example.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.TestRequestBodyDTO;
import com.example.ecommerce.dto.ResponseDTO;

@RestController
@RequestMapping("test")
public class TestController {
	
	@GetMapping("/testGetMapping")
	public String testController() {
		return "hello spring! testGetMapping";
	}
	
	@GetMapping("/{id}")
	public String testControllerWithPathVariable(@PathVariable(required = false) int id) {
		return "hello spring! ID: " + id;
	}
	
	@GetMapping("/testRequestParam")
	public String testControllerWithRequestParam(@RequestParam(required = false) int id) {
		return "hello spring! ID: " + id;
	}
	
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hello spring! ID : " + testRequestBodyDTO.getId() + "Message: " + testRequestBodyDTO.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody() {
		List<String> list = new ArrayList<>();
		list.add("Hello spring! Im responseDTO!");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return response;
		
	}
	
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity() {
		List<String> list = new ArrayList<>();
		list.add("Hello spring! Im response Entity and this is 200!!");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
}
