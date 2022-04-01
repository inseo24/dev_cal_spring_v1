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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "Comment")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100, nullable = false)
	private String comment;

	private String userId;

	@Builder
	public CommentEntity(String comment, String userId, String boardId) {
		this.comment = comment;
		this.userId = userId;
		this.boardId = boardId;
		this.isDeleted = false;
	}

	private String boardId;

	private boolean isDeleted;

	private LocalDateTime deletedAt;

	@CreationTimestamp
	private LocalDateTime createdTime;

	@PrePersist
	public void createdTime() {
		this.createdTime = LocalDateTime.now();
	}

	public void delete() {
		this.isDeleted = true;
		this.deletedAt = LocalDateTime.now();
	}

	public void update(String comment) {
		this.comment = comment;
	}
}
