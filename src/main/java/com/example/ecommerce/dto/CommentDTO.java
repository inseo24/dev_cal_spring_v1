package com.example.ecommerce.dto;

import com.example.ecommerce.model.BoardEntity;
import com.example.ecommerce.model.CommentEntity;


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
	
	public CommentDTO(final CommentEntity entity) {
		this.boardId = entity.getBoard().getBoardId();
		this.comment= entity.getComment();
	}
	
	public static CommentEntity toEntity(final CommentDTO dto) {
		return CommentEntity.builder()
				.comment(dto.getComment())
				.build();
	}
	
	
}
