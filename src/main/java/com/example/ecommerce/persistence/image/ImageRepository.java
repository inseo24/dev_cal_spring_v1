package com.example.ecommerce.persistence.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageJpaEntity, String> {

    Optional<ImageJpaEntity> findByBoardId(Long boardId);
}
