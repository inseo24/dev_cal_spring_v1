package com.example.ecommerce.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.ecommerce.persistence.ScrapRepository;

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
	public void retrieve(String userId) {
		scrapRepository.retrieveScrap(userId);
	}
	
}
