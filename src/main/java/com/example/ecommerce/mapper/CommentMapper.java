package com.example.ecommerce.mapper;

import com.example.ecommerce.domain.Comment;
import com.example.ecommerce.domain.Image;
import com.example.ecommerce.persistence.comment.CommentJpaEntity;
import com.example.ecommerce.persistence.image.ImageJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentJpaEntity mapToJpaEntity(Comment comment) {
        return CommentJpaEntity.builder()
                .boardId(comment.getBoardId())
                .comment(comment.getComment())
                .userId(comment.getUserId())
                .build();
    }

    public Comment mapToDomain(CommentJpaEntity entity) {
        return Comment.builder()
                .boardId(entity.getBoardId())
                .comment(entity.getComment())
                .userId(entity.getUserId())
                .isDeleted(entity.isDeleted())
                .build();
    }
}
