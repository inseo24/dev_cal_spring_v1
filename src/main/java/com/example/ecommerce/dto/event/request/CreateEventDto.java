package com.example.ecommerce.dto.event.request;

import com.example.ecommerce.domain.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateEventDto {

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String host;
    private String timeRequired;
    private String cost;
    private String limitPersonnel;
    private String relatedLink;

    public static Event toDomain(final CreateEventDto dto) {
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
