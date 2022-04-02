package com.example.ecommerce.persistence.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	List<CommentEntity> findAllByBoardId(String boardId);
	CommentEntity findByIdAndUserId(int id, String userId);
}
