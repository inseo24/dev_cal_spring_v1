package com.example.ecommerce.dto.board.response;

import com.example.ecommerce.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String title;
    private String content;
    private String userId;

    @Builder
    public BoardResponseDto(final Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userId = board.getUserId();
    }
}
