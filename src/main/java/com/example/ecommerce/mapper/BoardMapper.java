package com.example.ecommerce.mapper;

import com.example.ecommerce.domain.Board;
import com.example.ecommerce.persistence.board.BoardJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public BoardJpaEntity mapToJpaEntity(Board board) {
        return BoardJpaEntity.builder()
                .userId(board.getUserId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    public Board mapToDomain(BoardJpaEntity entity) {
        return Board.builder()
                .userId(entity.getUserId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
    }

}
