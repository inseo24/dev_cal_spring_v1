package com.example.ecommerce.dto.scrap;

import com.example.ecommerce.domain.Scrap;
import com.example.ecommerce.persistence.event.EventJpaEntity;
import com.example.ecommerce.persistence.scrap.ScrapJpaEntity;

import lombok.*;

@NoArgsConstructor
@Getter
public class ScrapDTO {

	private Long eventId;
	private String userId;

	@Builder
	public ScrapDTO(final Scrap scrap) {
		this.eventId = scrap.getEventId();
		this.userId = scrap.getUserId();
	}

	public static Scrap toDomain(final ScrapDTO scrapDTO) {
		return Scrap.builder()
				.userId(scrapDTO.getUserId())
				.eventId(scrapDTO.eventId)
				.build();
	}
}
