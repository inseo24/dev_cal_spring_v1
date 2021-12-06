package com.example.ecommerce.dto;

import java.time.LocalDateTime;

import javax.persistence.Lob;

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
	private LocalDateTime createdTime;
	private LocalDateTime modified_date;
	
	@Lob
	private String imgUrl;
	
	public BoardDTO(final BoardEntity entity) {
		this.boardId = entity.getBoardId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.createdTime = entity.getCreatedTime();
		this.modified_date = entity.getModified_date();
		this.imgUrl = entity.getImgUrl();
	}
	
	public static BoardEntity toEntity(final BoardDTO dto) {
		return BoardEntity.builder()
				.boardId(dto.getBoardId())
				.title(dto.getTitle())
				.content(dto.getContent())
				.modified_date(dto.getModified_date())
				.createdTime(dto.getCreatedTime())
				.imgUrl(dto.getImgUrl())
				.build();
	}
}
