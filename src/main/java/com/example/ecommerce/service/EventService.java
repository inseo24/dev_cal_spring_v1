package com.example.ecommerce.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.EventEntity;
import com.example.ecommerce.persistence.EventRepository;


@Service
public class EventService {

	@Autowired
	private EventRepository repo;

	public List<EventEntity> retrieve(){
		return repo.findAll();
	}
	
	public List<EventEntity> create(final EventEntity entity) {
		repo.save(entity);
		return repo.findByEventId(entity.getEventId());
	}
	
	public List<EventEntity> retrieve(String event){
		return repo.findByTitleContains(event);
	}
}
