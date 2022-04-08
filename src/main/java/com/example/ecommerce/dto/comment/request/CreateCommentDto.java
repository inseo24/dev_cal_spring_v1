package com.example.ecommerce.dto.comment.request;

import com.example.ecommerce.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentDto {
    private String comment;
    private String userId;
    private Long boardId;

    // TODO controller 에서 값을 넘겨주는게 나을까, 도메인으로 변경해서 넘겨주는게 나을까? 잘 모르겠다.
//    public static Comment toDomain(final CreateCommentDto dto) {
//        return Comment.builder()
//                .comment(dto.getComment())
//                .userId(dto.getUserId())
//                .boardId(dto.getBoardId())
//                .build();
//    }
}
