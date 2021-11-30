package com.example.ecommerce.dto;
import java.time.LocalDateTime;

import com.example.ecommerce.model.EventEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventDTO {

	private String eventId;
	private String title;
	private LocalDateTime start;
	private LocalDateTime end;
	private Long createdTime;
	private String host;
	private String time_required;
	private String cost;
	private String limit_personnel;
	private String related_link;
	
	public EventDTO(final EventEntity entity) {
		this.eventId = entity.getEventId();
		this.title = entity.getTitle();
		this.start = entity.getStart();
		this.end = entity.getEnd();
		this.host = entity.getHost();
		this.time_required = entity.getTime_required();
		this.cost = entity.getCost();
		this.limit_personnel = entity.getLimit_personnel();
		this.related_link = entity.getRelated_link();
	}
	
	public static EventEntity toEntity(final EventDTO dto ) {
		return EventEntity.builder()
				.eventId(dto.getEventId())
				.title(dto.getTitle())
				.start(dto.getStart())
				.cost(dto.getCost())
				.end(dto.getEnd())
				.host(dto.getHost())
				.time_required(dto.time_required)
				.limit_personnel(dto.limit_personnel)
				.related_link(dto.related_link)
				.build();
	}
}
