package com.example.ecommerce.dto.board;

import com.example.ecommerce.domain.Board;
import lombok.*;

@Getter
@NoArgsConstructor
public class BoardDTO {
	private String title;
	private String content;
	private String userId;
	private Long imageId;

	@Builder
	public BoardDTO(final Board entity) {
		this.imageId = entity.getImageId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.userId = entity.getUserId();
	}

	public static Board toDomain(final BoardDTO dto) {
		return Board.builder()
				.title(dto.getTitle())
				.userId(dto.getUserId())
				.content(dto.getContent())
				.imageId(dto.getImageId())
				.build();
	}
}
