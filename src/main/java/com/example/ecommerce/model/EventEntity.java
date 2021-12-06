package com.example.ecommerce.model;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

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
@Table(name = "Event")
public class EventEntity {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String eventId;
	
	
	private String title;
	private LocalDateTime start;
	private LocalDateTime end;
	private LocalDateTime createdTime;
	
	private String host;
	private String time_required;
	private String cost;
	private String limit_personnel;
	private String related_link;
	

	@PrePersist
	public void createdTime() {
		this.createdTime = LocalDateTime.now();
	}

}
