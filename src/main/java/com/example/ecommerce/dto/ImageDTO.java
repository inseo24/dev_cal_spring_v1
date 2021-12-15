package com.example.ecommerce.dto;


import org.springframework.web.multipart.MultipartFile;

import com.example.ecommerce.model.ImageEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageDTO {
	
	private MultipartFile file;
	private String boardId;	
	
	
	public static ImageEntity toEntity(String type, String name, String boardId) {
		return ImageEntity.builder()
				.name(name)
				.type(type)
				.boardId(boardId)
				.build();
	}
}
