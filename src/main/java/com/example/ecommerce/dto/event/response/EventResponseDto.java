package com.example.ecommerce.dto.event.response;

import com.example.ecommerce.domain.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventResponseDto {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String host;
    private String timeRequired;
    private String cost;
    private String limitPersonnel;
    private String relatedLink;

    @Builder
    public EventResponseDto(final Event event) {
        this.title = event.getTitle();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.host = event.getHost();
        this.timeRequired = event.getTimeRequired();
        this.cost = event.getCost();
        this.limitPersonnel = event.getLimitPersonnel();
        this.relatedLink = event.getRelatedLink();
    }
}
