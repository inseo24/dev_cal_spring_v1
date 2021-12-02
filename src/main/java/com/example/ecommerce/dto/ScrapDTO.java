package com.example.ecommerce.dto;

import com.example.ecommerce.model.EventEntity;
import com.example.ecommerce.model.ScrapEntity;
import com.example.ecommerce.model.UserEntity;

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
	private EventEntity event;
	private UserEntity user;
	
	public ScrapDTO(final ScrapEntity entity) {
		this.scrapId = entity.getScrapId();
		this.event = entity.getEvent();
		this.user = entity.getUser();
	}
	
	
	public static ScrapEntity toEntity(final ScrapDTO dto) {
		return ScrapEntity.builder()
				.scrapId(dto.getScrapId())
				.event(dto.getEvent())
				.user(dto.getUser())
				.build();
	}

}
