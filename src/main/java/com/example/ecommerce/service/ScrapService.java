package com.example.ecommerce.service;


import java.util.List;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.ecommerce.persistence.scrap.ScrapJpaEntity;
import com.example.ecommerce.persistence.scrap.ScrapRepository;


@Service
@RequiredArgsConstructor
public class ScrapService {
	
	private final ScrapRepository scrapRepository;
	
	@Transactional
	public void scrap(String eventId, String userId) {
		scrapRepository.scrap(eventId, userId);
	}
	
	@Transactional
	public void unscrap(String eventId, String userId) {
		scrapRepository.unscrap(eventId, userId);
	}
	
	@Transactional
	public List<ScrapJpaEntity> retrieve(String userId) {
		return scrapRepository.retrieveScrap(userId);
	}
	
}
