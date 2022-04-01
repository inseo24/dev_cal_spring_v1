package com.example.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.ecommerce.dto.CMResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequiredArgsConstructor
public class EventController {

	public EventService eventService;

	@GetMapping
	public ResponseEntity<?> retrieveBoardList() {

		List<EventEntity> entities = eventService.retrieve();

		List<EventDTO> dtos = entities.stream().map(EventDTO::new).collect(Collectors.toList());

		ResponseDTO<EventDTO> response = ResponseDTO.<EventDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping
	public ResponseEntity<?> createEvent(@RequestBody EventDTO dto) {
		try {
			EventEntity entity = EventDTO.toEntity(dto);
			List<EventEntity> entities = eventService.create(entity);
			List<EventDTO> dtos = entities.stream().map(EventDTO::new).collect(Collectors.toList());
			ResponseDTO<EventDTO> response = ResponseDTO.<EventDTO>builder().data(dtos).build();
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<EventDTO> response = ResponseDTO.<EventDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}

	}
	
	@GetMapping("/{event}")
	public ResponseEntity<?> retreive(@PathVariable String event){
		try {
			List<EventEntity> entities = eventService.retrieve(event);
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
