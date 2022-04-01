package com.example.ecommerce.dto;

import com.example.ecommerce.model.CommentEntity;
import com.example.ecommerce.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTO {
	private String comment;
	private String boardId;
	private int id;
	private String userId;
	
	public CommentDTO(final CommentEntity entity) {
		this.boardId = entity.getBoardId();
		this.userId = entity.getUserId();
		this.id = entity.getId();
		this.comment= entity.getComment();
	}
}
