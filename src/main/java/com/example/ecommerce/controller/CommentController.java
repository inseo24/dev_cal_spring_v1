package com.example.ecommerce.controller;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.CMResponseDTO;
import com.example.ecommerce.dto.CommentDTO;
import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.model.CommentEntity;
import com.example.ecommerce.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	public CommentService service;
	
	@GetMapping("/{boardId}")
	public ResponseEntity<?> retrieveComment(@PathVariable String boardId){
		List<CommentEntity> entities = service.retrieve(boardId);
		
		List<CommentDTO> dtos = entities.stream().map(CommentDTO::new).collect(Collectors.toList());
		
		ResponseDTO<CommentDTO> response = ResponseDTO.<CommentDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	
	@PostMapping
	public ResponseEntity<?> commentSave(@AuthenticationPrincipal String userId, @RequestBody CommentDTO commentDTO) {
		
		log.info("commentdto : " + commentDTO);
		String comment = commentDTO.getComment();
		String boardId = commentDTO.getBoardId();
		
		
		CommentEntity commentEntity = service.create(comment, boardId, userId);
		
		return new ResponseEntity<> (new CMResponseDTO<>(1, "comment success", commentEntity), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteComment() {
		return null;
	}
}
