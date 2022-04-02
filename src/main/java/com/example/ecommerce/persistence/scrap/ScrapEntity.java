package com.example.ecommerce.persistence.scrap;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.example.ecommerce.persistence.event.EventEntity;
import com.example.ecommerce.persistence.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scrapId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id")
	@JsonIgnoreProperties({"event_id"})
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
