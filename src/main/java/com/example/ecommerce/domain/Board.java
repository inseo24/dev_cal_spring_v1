package com.example.ecommerce.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    private Long boardId;
    private String userId;
    private String title;
    private String content;

    @Builder
    public Board(Long boardId, String userId, String title, String content) {
        this.boardId = boardId;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
