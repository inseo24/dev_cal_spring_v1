package com.example.ecommerce.model;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Scrap", uniqueConstraints = {
		@UniqueConstraint(
		name="scrap_uk", 
		columnNames = {"user_id", "event_id"}
		)
	}
)
public class ScrapEntity {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String scrapId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id")
	private EventEntity event;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	private LocalDateTime createdTime; 
	
	@PrePersist
	public void createdTime() {
		this.createdTime = LocalDateTime.now();
	}
	

	
}
