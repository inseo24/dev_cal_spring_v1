package com.example.ecommerce.dto.image.request;

import com.example.ecommerce.domain.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class CreateImageDto {

    private MultipartFile file;

    @Builder
    public static Image toDomain(final CreateImageDto dto) {
        return Image.builder()
                .file(dto.getFile())
                .build();
    }
}
