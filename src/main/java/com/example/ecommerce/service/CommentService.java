package com.example.ecommerce.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.ecommerce.handler.ex.CustomApiException;
import com.example.ecommerce.persistence.board.BoardJpaEntity;
import com.example.ecommerce.persistence.comment.CommentJpaEntity;
import com.example.ecommerce.persistence.board.BoardRepository;
import com.example.ecommerce.persistence.comment.CommentRepository;
import com.example.ecommerce.persistence.user.UserRepository;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public CommentJpaEntity create(String comment, String boardId, String userId) {

        BoardJpaEntity boardEntity = boardRepository.getById(boardId);

        UserEntity user = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiException("user not found");
        });

        CommentJpaEntity entity = new CommentJpaEntity(comment, userId, boardId);
        return commentRepository.save(entity);
    }

    public List<CommentJpaEntity> retrieve(final String boardId) {
        return commentRepository.findAllByBoardId(boardId);
    }

    @Transactional
    public List<CommentJpaEntity> retrieve() {
        return commentRepository.findAll();
    }

    @Transactional
    public void delete(int id, String userId) {
        final CommentJpaEntity entity = commentRepository.findById(id).orElseThrow();
        if (!Objects.equals(entity.getUserId(), userId)) throw new CustomApiException("not same user");
        entity.delete();
    }

    @Transactional
    public void update(String comment, Integer id) {
        CommentJpaEntity entity = commentRepository.findById(id).orElseThrow();
        entity.update(comment);
    }

}
