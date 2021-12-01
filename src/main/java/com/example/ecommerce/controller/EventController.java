package com.example.ecommerce.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.EventDTO;
import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.model.EventEntity;
import com.example.ecommerce.service.EventService;

@RestController
@RequestMapping("event")
public class EventController {
	
	@Autowired
	public EventService service;

	@GetMapping
	public ResponseEntity<?> retrieveBoardList() {

		List<EventEntity> entities = service.retrieve();

		List<EventDTO> dtos = entities.stream().map(EventDTO::new).collect(Collectors.toList());

		ResponseDTO<EventDTO> response = ResponseDTO.<EventDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);
		
	}
	
	@PostMapping
	public ResponseEntity<?> createEvent(@RequestBody EventDTO dto) {
		
		
		try {
			
			EventEntity entity = EventDTO.toEntity(dto);
	
			List<EventEntity> entities = service.create(entity);

			List<EventDTO> dtos = entities.stream().map(EventDTO::new).collect(Collectors.toList());

			ResponseDTO<EventDTO> response = ResponseDTO.<EventDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			
			String error = e.getMessage();
			ResponseDTO<EventDTO> response = ResponseDTO.<EventDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
	
		}

	}

}
