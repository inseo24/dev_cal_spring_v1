package com.example.ecommerce.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.model.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	List<CommentEntity> findAllByBoardId(String boardId);
	CommentEntity findByIdAndUserId(int id, String userId);
}
