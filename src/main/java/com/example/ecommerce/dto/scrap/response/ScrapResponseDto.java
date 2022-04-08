package com.example.ecommerce.dto.scrap.response;

import com.example.ecommerce.domain.Scrap;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ScrapResponseDto {
    private Long eventId;
    private String userId;

    @Builder
    public ScrapResponseDto(final Scrap scrap) {
        this.eventId = scrap.getEventId();
        this.userId = scrap.getUserId();
    }
}
