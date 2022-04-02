package com.example.ecommerce.persistence.scrap;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Scrap", uniqueConstraints = {
		@UniqueConstraint(
		name="scrap_uk", 
		columnNames = {"userId", "eventId"}
		)
	}
)
public class ScrapJpaEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scrapId;
	private Long eventId;
	private String userId;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Builder
	public ScrapJpaEntity(Long scrapId, Long eventId, String userId) {
		this.scrapId = scrapId;
		this.eventId = eventId;
		this.userId = userId;
	}
}
