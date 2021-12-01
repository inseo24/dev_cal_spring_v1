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
@Table(name = "Board")
public class BoardEntity {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String boardId;
	
	
	
	private String userId;
	private String title;
	private String content;
	private LocalDateTime createdTime; 
	private LocalDateTime modified_date;
	
	@PrePersist
	public void createdTime() {
		this.createdTime = LocalDateTime.now();
	}
}
