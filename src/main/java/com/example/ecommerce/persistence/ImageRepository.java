package com.example.ecommerce.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce.model.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long>{

	@Modifying
	@Query(value = "INSERT INTO board_image(board_id, file_id) VALUES(:boardId, :fileId)", nativeQuery = true)
	void image(@Param("boardId") String boardId, @Param("fileId") Long fileId);
	
}
