package com.example.ecommerce.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.EventEntity;
import com.example.ecommerce.persistence.EventRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventService {

	@Autowired
	private EventRepository repo;
	

	public List<EventEntity> retrieve(){
		return repo.findAll();
	}
	
	public List<EventEntity> create(final EventEntity entity) {
	
		repo.save(entity);

		log.info("Entity Id : {} is saved", entity.getEventId());
		
		return repo.findByEventId(entity.getEventId());
	}
	
	@Transactional
	public List<EventEntity> retrieve(String event){
		return repo.findByTitleContains(event);
	}
		

}
