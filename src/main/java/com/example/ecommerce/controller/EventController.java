package com.example.ecommerce.controller;


import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.event.EventDTO;
import com.example.ecommerce.service.EventService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<?> retrieve() {
        List<EventDTO> response = eventService.retrieve().stream().map(EventDTO::new).collect(Collectors.toList());
        return ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EventDTO eventDTO) {
        return ok().body(eventService.create(EventDTO.toDomain(eventDTO)));
    }

    @GetMapping("/{title}")
    public ResponseEntity<?> retrieveByEventTitleContains(@PathVariable String title) {
        return ok().body(eventService.retrieveByEventTitleContains(title));
    }

}
