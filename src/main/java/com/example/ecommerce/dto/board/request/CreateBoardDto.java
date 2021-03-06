package com.example.ecommerce.dto.board.request;

import com.example.ecommerce.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateBoardDto {
    private String title;
    private String content;
    private String userId;

    public static Board toDomain(final CreateBoardDto dto) {
        return Board.builder()
                .title(dto.getTitle())
                .userId(dto.getUserId())
                .content(dto.getContent())
                .build();
    }
}