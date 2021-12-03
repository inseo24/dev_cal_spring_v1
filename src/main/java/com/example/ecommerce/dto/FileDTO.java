package com.example.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import com.example.ecommerce.model.FileEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileDTO {
	
	private MultipartFile file;
	private String boardId;
	
	public FileEntity toEntity() {
		return FileEntity.builder()
				.boardId(boardId)
				.build();
	}
	
	
	public FileEntity toEntity(String imageUrl, String fileType, String boardId) {
		return FileEntity.builder()
				.name(imageUrl)
				.boardId(boardId)
				.type(fileType)
				.build();
	}
	
}
