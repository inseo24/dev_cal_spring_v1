package com.example.ecommerce.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.handler.ex.CustomApiException;
import com.example.ecommerce.model.BoardEntity;
import com.example.ecommerce.model.CommentEntity;
import com.example.ecommerce.model.UserEntity;
import com.example.ecommerce.persistence.BoardRepository;
import com.example.ecommerce.persistence.CommentRepository;
import com.example.ecommerce.persistence.UserRepository;


@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	
	@Transactional
	public CommentEntity create(String comment, String boardId, String userId) {
				
		BoardEntity boardEntity = boardRepo.getById(boardId);
		
		UserEntity user = userRepo.findById(userId).orElseThrow(() -> {
			throw new CustomApiException("유저 아이디를 찾을 수가 없습니다.");
		});

		CommentEntity entity = new CommentEntity();
		entity.setBoard(boardEntity);
		entity.setUser(user);
		entity.setComment(comment);
		
		return commentRepo.save(entity);
	}
	
	@Transactional
	public List<CommentEntity> retrieve(final String boardId) {
		return commentRepo.retrieveComment(boardId);
	}

	@Transactional
	public CommentEntity delete() {
		return null;
	}
	

	
}
