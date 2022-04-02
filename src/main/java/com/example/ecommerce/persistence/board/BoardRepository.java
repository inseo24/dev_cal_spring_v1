package com.example.ecommerce.persistence.board;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String> {

	List<BoardEntity> findByBoardId(String boardId);

}
