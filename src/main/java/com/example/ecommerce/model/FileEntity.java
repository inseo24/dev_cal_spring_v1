package com.example.ecommerce.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "files")
public class FileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileId; 
	
	private String type;
	private String name;
	
	private String boardId;
	
	private LocalDateTime createdTime; 

	@PrePersist
	public void createdTime() {
		this.createdTime = LocalDateTime.now();
	}
	
}
