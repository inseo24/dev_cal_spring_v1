package com.example.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.ecommerce.model.BoardEntity;
import com.example.ecommerce.model.CommentEntity;
import com.example.ecommerce.model.ImageEntity;
import com.example.ecommerce.model.UserEntity;

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
	private UserEntity userId;
	private List<CommentEntity> comments;
	private List<ImageEntity> images;
	private int boardNumber;
	private LocalDateTime createdTime;
	private LocalDateTime modified_date;
	
	public BoardDTO(final BoardEntity entity) {
		this.boardId = entity.getBoardId();
		this.boardNumber = entity.getBoardNumber();
		this.comments = entity.getComments();
		this.images = entity.getImages();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.userId = entity.getUserId();
		this.createdTime = entity.getCreatedTime();
		this.modified_date = entity.getModified_date();
	}
	
	public static BoardEntity toEntity(final BoardDTO dto) {
		return BoardEntity.builder()
				.boardId(dto.getBoardId())
				.boardNumber(dto.getBoardNumber())
				.title(dto.getTitle())
				.comments(dto.getComments())
				.images(dto.getImages())
				.userId(dto.getUserId())
				.content(dto.getContent())
				.modified_date(dto.getModified_date())
				.createdTime(dto.getCreatedTime())
				.build();
	}
}
