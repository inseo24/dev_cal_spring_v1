package com.example.ecommerce.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.ecommerce.persistence.event.EventJpaEntity;
import com.example.ecommerce.persistence.event.EventRepository;


@Service
@RequiredArgsConstructor
public class EventService {

	private final EventRepository eventRepository;

	public List<EventJpaEntity> retrieve(){
		return eventRepository.findAll();
	}
	
	public List<EventJpaEntity> create(final EventJpaEntity entity) {
		eventRepository.save(entity);
		return eventRepository.findByEventId(entity.getEventId());
	}
	
	public List<EventJpaEntity> retrieve(String event){
		return eventRepository.findByTitleContains(event);
	}
}
