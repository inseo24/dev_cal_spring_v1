package com.example.ecommerce.dto.comment;

import com.example.ecommerce.persistence.comment.CommentJpaEntity;

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
	
	public CommentDTO(final CommentJpaEntity entity) {
		this.boardId = entity.getBoardId();
		this.userId = entity.getUserId();
		this.id = entity.getId();
		this.comment= entity.getComment();
	}
}
