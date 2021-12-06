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
	public int id;
	
	private BoardEntity board;
	
	public CommentDTO(final CommentEntity entity) {
		this.board = entity.getBoard();
		this.comment= entity.getComment();
		this.id = entity.getId();
	}
	
	public static CommentEntity toEntity(final CommentDTO dto) {
		return CommentEntity.builder()
				.board(dto.getBoard())
				.id(dto.getId())
				.comment(dto.getComment())
				.build();
	}
	
	
}
