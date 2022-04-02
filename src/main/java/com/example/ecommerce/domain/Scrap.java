package com.example.ecommerce.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scrap {
    private Long eventId;
    private String userId;

    @Builder
    public Scrap(Long eventId, String userId) {
        this.eventId = eventId;
        this.userId = userId;
    }
}
