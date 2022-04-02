package com.example.ecommerce.service;


import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.example.ecommerce.domain.Scrap;
import com.example.ecommerce.mapper.ScrapMapper;
import com.example.ecommerce.persistence.scrap.ScrapJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.ecommerce.persistence.scrap.ScrapRepository;


@Service
@RequiredArgsConstructor
public class ScrapService {

	private final ScrapRepository scrapRepository;
	private final ScrapMapper scrapMapper;
	
	@Transactional
	public void scrap(Long eventId, String userId) {
		ScrapJpaEntity entity = ScrapJpaEntity.builder().eventId(eventId).userId(userId).build();
		scrapRepository.save(entity);
	}
	
	@Transactional
	public void unScrap(Long eventId, String userId) {
		ScrapJpaEntity entity = scrapRepository.findByUserIdAndEventId(eventId, userId).orElseThrow();
		scrapRepository.deleteById(entity.getScrapId());
	}
	
	@Transactional
	public List<Scrap> retrieve(String userId) {
		return scrapRepository.findAllByUserId(userId).stream().map(scrapMapper::mapToDomain).collect(Collectors.toList());
	}
}
