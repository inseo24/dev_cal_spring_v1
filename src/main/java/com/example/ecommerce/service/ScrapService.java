package com.example.ecommerce.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.ScrapEntity;
import com.example.ecommerce.persistence.ScrapRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScrapService {
	
	@Autowired
	private ScrapRepository scrapRepository;
	
	@Transactional
	public void scrap(String eventId, String userId) {
		scrapRepository.scrap(eventId, userId);
	}
	
	@Transactional
	public void unscrap(String eventId, String userId) {
		scrapRepository.unscrap(eventId, userId);
	}
	
	@Transactional
	public List<ScrapEntity> retrieve(String userId) {
		
		log.info("userId : " + userId);
		
		return scrapRepository.retrieveScrap(userId);
	}
	
}
