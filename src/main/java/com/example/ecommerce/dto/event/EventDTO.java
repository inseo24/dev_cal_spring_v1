package com.example.ecommerce.dto.event;

import java.time.LocalDateTime;

import com.example.ecommerce.domain.Event;
import com.example.ecommerce.persistence.event.EventJpaEntity;

import lombok.*;

@Getter
@NoArgsConstructor
public class EventDTO {

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String host;
    private String timeRequired;
    private String cost;
    private String limitPersonnel;
    private String relatedLink;

	@Builder
    public EventDTO(final Event event) {
        this.title = event.getTitle();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.host = event.getHost();
        this.timeRequired = event.getTimeRequired();
        this.cost = event.getCost();
        this.limitPersonnel = event.getLimitPersonnel();
        this.relatedLink = event.getRelatedLink();
    }

	public static Event toDomain(final EventDTO dto) {
		return Event.builder()
				.title(dto.getTitle())
				.startDate(dto.getStartDate())
				.endDate(dto.getEndDate())
				.host(dto.getHost())
				.timeRequired(dto.getTimeRequired())
				.cost(dto.getCost())
				.limitPersonnel(dto.getLimitPersonnel())
				.relatedLink(dto.getRelatedLink())
				.build();
	}
}
