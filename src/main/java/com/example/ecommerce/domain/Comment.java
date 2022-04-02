package com.example.ecommerce.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    private String comment;
    private String userId;
    private Long boardId;
    private boolean isDeleted;

    @Builder
    public Comment(String comment, String userId, Long boardId, boolean isDeleted) {
        this.comment = comment;
        this.userId = userId;
        this.boardId = boardId;
        this.isDeleted = isDeleted;
    }
}
