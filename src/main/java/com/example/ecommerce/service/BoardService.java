package com.example.ecommerce.service;

import java.time.LocalDateTime;
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
		
		// �Ѱܹ��� ��ƼƼ id�� �̿��� BoardEntity�� �����´�. �������� �ʴ� ��ƼƼ�� ������Ʈ�� �� �����Ƿ�!
		final Optional<BoardEntity> original = repo.findById(entity.getBoardId());
		
		original.ifPresent(board -> {
			// ��ȯ�� ��ƼƼ�� ���K�� ���� �� ��ƼƼ ������ ���� ����
			board.setTitle(entity.getTitle());
			board.setContent(entity.getContent());
			board.setModified_date(LocalDateTime.now());
		
			// db�� ���ο� ���� ����
			repo.save(board);
		});
		
		// retrieve �޼��带 �̿��� ������� ��� ����Ʈ�� ����
		return retrieve(entity.getUserId());
	}
	
	// delete
	public List<BoardEntity> deleteBoard(final BoardEntity entity){
		validate(entity);
		
		try {
			// ����
			repo.delete(entity);
			
		} catch(Exception e) {
			// exception �߻� �� id�� exception�� �α�
			log.error("error deleting entity", entity.getBoardId(), e);
			
			// ��Ʈ�ѷ��� exception ������
			// db ���� ������ ĸ��ȭ�ϱ� ���� e�� �������� �ʰ� �� exception ������Ʈ ����
			throw new RuntimeException("error deleting entity" + entity.getBoardId());
		}
		
		// �� list ��ȯ
		return retrieve(entity.getUserId());
	}
}