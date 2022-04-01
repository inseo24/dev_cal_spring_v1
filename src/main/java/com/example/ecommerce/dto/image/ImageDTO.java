package com.example.ecommerce.dto.image;


import org.springframework.web.multipart.MultipartFile;

import com.example.ecommerce.persistence.image.ImageEntity;

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
