package com.example.ecommerce.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    private MultipartFile file;
    private Long boardId;
    private String type;
    private String name;

    @Builder
    public Image(MultipartFile file, Long boardId, String name, String type) {
        this.boardId = boardId;
        this.file = file;
        this.type = type;
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }
}
