package com.example.ecommerce.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    private String userId;
    private Long imageId;
    private String title;
    private String content;

    @Builder
    public Board(String userId, Long imageId, String title, String content) {
        this.userId = userId;
        this.imageId = imageId;
        this.title = title;
        this.content = content;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
