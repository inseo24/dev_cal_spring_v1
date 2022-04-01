package com.example.ecommerce.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.CommentDTO;
import com.example.ecommerce.handler.ex.CustomApiException;
import com.example.ecommerce.model.BoardEntity;
import com.example.ecommerce.model.CommentEntity;
import com.example.ecommerce.model.UserEntity;
import com.example.ecommerce.persistence.BoardRepository;
import com.example.ecommerce.persistence.CommentRepository;
import com.example.ecommerce.persistence.UserRepository;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public CommentEntity create(String comment, String boardId, String userId) {

        BoardEntity boardEntity = boardRepository.getById(boardId);

        UserEntity user = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiException("user not found");
        });

        CommentEntity entity = new CommentEntity(comment, userId, boardId);
        return commentRepository.save(entity);
    }

    public List<CommentEntity> retrieve(final String boardId) {
        return commentRepository.findAllByBoardId(boardId);
    }

    @Transactional
    public List<CommentEntity> retrieve() {
        return commentRepository.findAll();
    }

    @Transactional
    public void delete(int id, String userId) {
        final CommentEntity entity = commentRepository.findById(id).orElseThrow();
        if (!Objects.equals(entity.getUserId(), userId)) throw new CustomApiException("not same user");
        entity.delete();
    }

    @Transactional
    public void update(String comment, Integer id) {
        CommentEntity entity = commentRepository.findById(id).orElseThrow();
        entity.update(comment);
    }

}
