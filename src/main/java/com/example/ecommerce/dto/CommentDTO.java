package com.example.ecommerce.dto;

import com.example.ecommerce.domain.Comment;
import lombok.*;

@Getter
@NoArgsConstructor
public class CommentDTO {
	private String comment;
	private Long boardId;
	private String userId;

	@Builder
	public CommentDTO(final Comment comment) {
		this.boardId = comment.getBoardId();
		this.userId = comment.getUserId();
		this.comment= comment.getComment();
	}
}
