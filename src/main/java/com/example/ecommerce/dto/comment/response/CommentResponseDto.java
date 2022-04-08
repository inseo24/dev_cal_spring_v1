package com.example.ecommerce.dto.comment.response;

import com.example.ecommerce.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String comment;
    private String userId;
    private Long boardId;
    private boolean isDeleted;

    @Builder
    public CommentResponseDto(Comment comment) {
        this.comment = comment.getComment();
        this.userId = comment.getUserId();
        this.boardId = comment.getBoardId();
        this.isDeleted = comment.isDeleted();
    }
}
