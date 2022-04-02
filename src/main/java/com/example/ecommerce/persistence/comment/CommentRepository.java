package com.example.ecommerce.persistence.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentJpaEntity, Integer> {
	List<CommentJpaEntity> findAllByBoardId(String boardId);
	CommentJpaEntity findByIdAndUserId(int id, String userId);
}
