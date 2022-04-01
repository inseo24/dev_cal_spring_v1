package com.example.ecommerce.service;


import java.util.List;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.ScrapEntity;
import com.example.ecommerce.persistence.ScrapRepository;


@Service
@RequiredArgsConstructor
public class ScrapService {
	
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
		return scrapRepository.retrieveScrap(userId);
	}
	
}
