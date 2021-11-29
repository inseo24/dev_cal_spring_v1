package com.example.ecommerce.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.BoardEntity;
import com.example.ecommerce.persistence.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {

	@Autowired
	private BoardRepository repo;

	public String testService() {
		return null;
	}

	private void validate(final BoardEntity entity) {
		// validation
		if (entity == null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null");
		}

		if (entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
	
	
	// create
	public List<BoardEntity> createBoard(final BoardEntity entity) {
		
		validate(entity);
		
		repo.save(entity);

		log.info("Entity Id : {} is saved", entity.getUserId());

		return repo.findByBoardId(entity.getBoardId());
	}
	
	// retrieve
	public List<BoardEntity> retrieve(final String boardId){
		return repo.findAll(Sort.by("boardId").descending());
	}
	
	// retrieve
	public List<BoardEntity> retrieveItem(final String boardId){
		return repo.findByBoardId(boardId);
	}

	// update
	public List<BoardEntity> updateBoard(final BoardEntity entity){
		validate(entity);
		
		// 넘겨받은 엔티티 id를 이용해 BoardEntity를 가져온다. 존재하지 않는 엔티티는 업데이트할 수 없으므로!
		final Optional<BoardEntity> original = repo.findById(entity.getBoardId());
		
		original.ifPresent(board -> {
			// 반환된 엔티티가 존쟇면 값을 새 엔티티 값으로 덮어 씌움
			board.setTitle(entity.getTitle());
			board.setContent(entity.getContent());
			board.setModified_date(new Date().getTime());
		
			// db에 새로운 값을 저장
			repo.save(board);
		});
		
		// retrieve 메서드를 이용해 사용자의 모든 리스트를 리턴
		return retrieve(entity.getUserId());
	}
	
	// delete
	public List<BoardEntity> deleteBoard(final BoardEntity entity){
		validate(entity);
		
		try {
			// 삭제
			repo.delete(entity);
			
		} catch(Exception e) {
			// exception 발생 시 id와 exception을 로깅
			log.error("error deleting entity", entity.getBoardId(), e);
			
			// 컨트롤러로 exception 보내기
			// db 내부 로직을 캡슐화하기 위해 e를 리턴하지 않고 새 exception 오브젝트 리턴
			throw new RuntimeException("error deleting entity" + entity.getBoardId());
		}
		
		// 새 list 반환
		return retrieve(entity.getUserId());
	}
}
