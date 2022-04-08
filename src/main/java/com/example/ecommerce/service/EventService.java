package com.example.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.ecommerce.domain.Event;
import com.example.ecommerce.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.ecommerce.persistence.event.EventRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public List<Event> retrieve() {
        return eventRepository.findAll()
                .stream().map(eventMapper::mapToDomain).collect(Collectors.toList());
    }

    @Transactional
    public Event create(final Event event) {
        return eventMapper.mapToDomain(eventRepository.save(eventMapper.mapToJpaEntity(event)));
    }

    public List<Event> retrieveByEventTitleContains(String title) {
        return eventRepository.findByTitleContains(title)
                .stream().map(eventMapper::mapToDomain).collect(Collectors.toList());
    }
}
