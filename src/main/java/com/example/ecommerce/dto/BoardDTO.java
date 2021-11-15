package com.example.ecommerce.dto;

import com.example.ecommerce.model.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
	private String boardId;
	private String title;
	private String content;
	private Long createdTime;
	private Long modified_date;
	
	public BoardDTO(final BoardEntity entity) {
		this.boardId = entity.getBoardId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.createdTime = entity.getCreatedTime();
		this.modified_date = entity.getModified_date();
		
	}
	
	public static BoardEntity toEntity(final BoardDTO dto) {
		return BoardEntity.builder()
				.boardId(dto.getBoardId())
				.title(dto.getTitle())
				.content(dto.getContent())
				.modified_date(dto.getModified_date())
				.createdTime(dto.getCreatedTime())
				.build();
	}
}
