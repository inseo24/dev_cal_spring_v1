package com.example.ecommerce.persistence.board;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardJpaEntity, Long> {
    Optional<BoardJpaEntity> findByBoardId(Long boardId);
    Optional<BoardJpaEntity> findByBoardIdAndUserId(Long boardId, String UserId);
}
