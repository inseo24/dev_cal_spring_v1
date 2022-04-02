package com.example.ecommerce.dto.scrap;

import com.example.ecommerce.persistence.event.EventJpaEntity;
import com.example.ecommerce.persistence.scrap.ScrapJpaEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScrapDTO {
	
	private int scrapId;
	private EventJpaEntity event;
	private UserEntity user;
	
	public ScrapDTO(final ScrapJpaEntity entity) {
		this.scrapId = entity.getScrapId();
		this.event = entity.getEvent();
		this.user = entity.getUser();
	}
	
	
	public static ScrapJpaEntity toEntity(final ScrapDTO dto) {
		return ScrapJpaEntity.builder()
				.scrapId(dto.getScrapId())
				.event(dto.getEvent())
				.user(dto.getUser())
				.build();
	}

}
