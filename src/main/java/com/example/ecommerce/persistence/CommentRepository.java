package com.example.ecommerce.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce.model.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	
	@Query(value = "SELECT * FROM comment WHERE board_id = :boardId", nativeQuery = true)
	List<CommentEntity> retrieveComment(@Param("boardId") String boardId);
}
