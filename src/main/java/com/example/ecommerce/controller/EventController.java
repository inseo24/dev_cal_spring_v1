package com.example.ecommerce.controller;


import java.util.stream.Collectors;

import com.example.ecommerce.dto.event.request.CreateEventDto;
import com.example.ecommerce.dto.event.response.EventResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.service.EventService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<?> retrieve() {
        return ok().body(eventService.retrieve().stream()
                .map(EventResponseDto::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateEventDto eventDTO) {
        return ok().body(eventService.create(CreateEventDto.toDomain(eventDTO)));
    }

    @GetMapping("/{title}")
    public ResponseEntity<?> retrieveByEventTitleContains(@PathVariable String title) {
        return ok().body(eventService.retrieveByEventTitleContains(title).stream()
                .map(EventResponseDto::new).collect(Collectors.toList()));
    }
}
