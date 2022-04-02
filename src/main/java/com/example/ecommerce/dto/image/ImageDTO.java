package com.example.ecommerce.dto.image;

import com.example.ecommerce.domain.Image;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class ImageDTO {

    private MultipartFile file;
    private Long boardId;

    @Builder
    public static Image toDomain(final ImageDTO dto) {
        return Image.builder()
                .file(dto.getFile())
                .boardId(dto.getBoardId())
                .build();
    }
}
