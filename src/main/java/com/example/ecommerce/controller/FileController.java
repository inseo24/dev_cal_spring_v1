package com.example.ecommerce.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.FileDTO;
import com.example.ecommerce.service.FileService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("file")
public class FileController {
	
	@Autowired
	public FileService service;
	
	@PostMapping("/image")
	public ResponseEntity<?> imageUpload(@AuthenticationPrincipal String userId, FileDTO fileDto) throws IllegalStateException, IOException {
		
		log.info("fileDto: " + fileDto);
		
	
		service.uploadImage(userId, fileDto);
			    
	    return new ResponseEntity<>("image ok", HttpStatus.OK);
	}

}