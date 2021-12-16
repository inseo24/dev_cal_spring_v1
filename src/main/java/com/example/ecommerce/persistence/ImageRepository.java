package com.example.ecommerce.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.model.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, String>{
	
	ImageEntity findByBoardId(String boardId);
}
