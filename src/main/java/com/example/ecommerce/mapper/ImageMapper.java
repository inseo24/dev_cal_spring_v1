package com.example.ecommerce.mapper;

import com.example.ecommerce.domain.Image;
import com.example.ecommerce.persistence.image.ImageJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageJpaEntity mapToJpaEntity(Image image) {
        return ImageJpaEntity.builder()
                .name(image.getName())
                .boardId(image.getBoardId())
                .build();
    }

    public Image mapToDomain(ImageJpaEntity entity) {
        return Image.builder()
                .name(entity.getName())
                .boardId(entity.getBoardId())
                .build();
    }

}
