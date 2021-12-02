package com.example.ecommerce.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.CMResponseDTO;
import com.example.ecommerce.service.ScrapService;

@RestController
@RequestMapping("/scrap")
public class ScrapController {
	
	@Autowired
	private ScrapService service;
	
	@PostMapping("/{eventId}")
	public ResponseEntity<?> scrap(@PathVariable String eventId, @AuthenticationPrincipal String userId){
		 service.scrap(eventId, userId); 
		return new ResponseEntity<>(new CMResponseDTO<>(1, "scrap ����", null), HttpStatus.OK);
	}
	
	@DeleteMapping("/{eventId}")
	public ResponseEntity<?> unscrap(@PathVariable String eventId, @AuthenticationPrincipal String userId){
		service.unscrap(eventId, userId);
		return new ResponseEntity<>(new CMResponseDTO<>(1, "unscrap ����", null), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getScrap(@AuthenticationPrincipal String userId){
		
		service.retrieve(userId);

		return new ResponseEntity<>(new CMResponseDTO<>(1, "��ũ�� ��ȸ ����", null), HttpStatus.OK);
	}

}