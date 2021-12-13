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
	private int id;
	
	public CommentDTO(final CommentEntity entity) {
		this.boardId = entity.getBoard().getBoardId();
		this.id = entity.getId();
		this.comment= entity.getComment();
	}
	
	public static CommentEntity toEntity(final CommentDTO dto) {
		return CommentEntity.builder()
				.id(dto.getId())
				.comment(dto.getComment())
				.build();
	}
	
	
}
