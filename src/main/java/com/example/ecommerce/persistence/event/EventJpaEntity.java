package com.example.ecommerce.persistence.event;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Event")
public class EventJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String host;
    private String timeRequired;
    private String cost;
    private String limitPersonnel;
    private String relatedLink;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public EventJpaEntity(String title, LocalDateTime startDate, LocalDateTime endDate, String host, String timeRequired, String cost, String limitPersonnel, String relatedLink) {
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
