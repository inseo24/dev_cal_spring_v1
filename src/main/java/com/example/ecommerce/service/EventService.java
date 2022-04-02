package com.example.ecommerce.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.ecommerce.persistence.event.EventEntity;
import com.example.ecommerce.persistence.event.EventRepository;


@Service
@RequiredArgsConstructor
public class EventService {

	private final EventRepository eventRepository;

	public List<EventEntity> retrieve(){
		return eventRepository.findAll();
	}
	
	public List<EventEntity> create(final EventEntity entity) {
		eventRepository.save(entity);
		return eventRepository.findByEventId(entity.getEventId());
	}
	
	public List<EventEntity> retrieve(String event){
		return eventRepository.findByTitleContains(event);
	}
}
