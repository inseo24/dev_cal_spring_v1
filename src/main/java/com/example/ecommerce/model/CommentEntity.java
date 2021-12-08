package com.example.ecommerce.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

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
@Table(name = "Comment")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100, nullable = false)
	private String comment;
	
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private UserEntity user;
	
	@JoinColumn(name = "boardId")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"boardId"})
	private BoardEntity board;
	
	private LocalDateTime createdTime;
	
	@PrePersist
	public void createdTime() {
		this.createdTime = LocalDateTime.now();
	}
}
