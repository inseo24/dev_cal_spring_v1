package com.example.ecommerce.persistence.image;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, String>{
	
	ImageEntity findByBoardId(String boardId);
}
