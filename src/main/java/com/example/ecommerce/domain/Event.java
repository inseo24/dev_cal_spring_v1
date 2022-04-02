package com.example.ecommerce.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String host;
    private String timeRequired;
    private String cost;
    private String limitPersonnel;
    private String relatedLink;

    @Builder
    public Event(String title, LocalDateTime startDate, LocalDateTime endDate, String host, String timeRequired, String cost, String limitPersonnel, String relatedLink) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.host = host;
        this.timeRequired = timeRequired;
        this.cost = cost;
        this.limitPersonnel = limitPersonnel;
        this.relatedLink = relatedLink;
    }
}
