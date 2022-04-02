package com.example.ecommerce.mapper;

import com.example.ecommerce.domain.Scrap;
import com.example.ecommerce.persistence.scrap.ScrapJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ScrapMapper {

    public ScrapJpaEntity mapToJpaEntity(Scrap scrap) {
        return ScrapJpaEntity.builder()
                .eventId(scrap.getEventId())
                .userId(scrap.getUserId())
                .build();
    }

    public Scrap mapToDomain(ScrapJpaEntity entity) {
        return Scrap.builder()
                .eventId(entity.getEventId())
                .userId(entity.getUserId())
                .build();
    }
}
