package com.example.ecommerce.dto.comment;

import com.example.ecommerce.domain.Comment;

import lombok.*;

@Getter
@NoArgsConstructor
public class CommentDTO {
	private String comment;
	private String userId;
	private Long boardId;
	private boolean isDeleted;

	@Builder
	public CommentDTO(Comment comment) {
		this.comment = comment.getComment();
		this.userId = comment.getUserId();
		this.boardId = comment.getBoardId();
		this.isDeleted = comment.isDeleted();
	}
}
