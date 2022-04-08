package com.example.ecommerce.dto.board.request;

import com.example.ecommerce.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateBoardDto {
    private String title;
    private String content;
    private String userId;
    private Long boardId;

    public static Board toDomain(final UpdateBoardDto dto) {
        return Board.builder()
                .title(dto.getTitle())
                .userId(dto.getUserId())
                .content(dto.getContent())
                .boardId(dto.getBoardId())
                .build();
    }
}
