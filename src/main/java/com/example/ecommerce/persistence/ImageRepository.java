package com.example.ecommerce.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.model.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, String>{
	
	Optional<ImageEntity> findByBoardId(String boardId);
}
