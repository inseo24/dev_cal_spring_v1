package com.example.ecommerce.mapper;

import com.example.ecommerce.domain.Event;
import com.example.ecommerce.domain.Image;
import com.example.ecommerce.persistence.event.EventJpaEntity;
import com.example.ecommerce.persistence.image.ImageJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventJpaEntity mapToJpaEntity(Event event) {
        return EventJpaEntity.builder()
                .title(event.getTitle())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .host(event.getHost())
                .timeRequired(event.getTimeRequired())
                .cost(event.getCost())
                .limitPersonnel(event.getLimitPersonnel())
                .build();
    }

    public Event mapToDomain(EventJpaEntity entity) {
        return Event.builder()
                .title(entity.getTitle())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .host(entity.getHost())
                .timeRequired(entity.getTimeRequired())
                .cost(entity.getCost())
                .limitPersonnel(entity.getLimitPersonnel())
                .build();
    }

}
