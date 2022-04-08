package com.example.ecommerce.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.ecommerce.domain.Comment;
import com.example.ecommerce.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.ecommerce.handler.ex.CustomApiException;
import com.example.ecommerce.persistence.comment.CommentJpaEntity;
import com.example.ecommerce.persistence.comment.CommentRepository;
import com.example.ecommerce.persistence.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public void create(String comment, Long boardId, String userId) {
        verifyUserId(userId);
        commentRepository.save(new CommentJpaEntity(comment, userId, boardId));
    }

    @Transactional(readOnly = true)
    public List<Comment> retrieveByBoardId(final String boardId) {
        return commentRepository.findAllByBoardId(boardId)
                .stream().map(commentMapper::mapToDomain).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Comment> retrieve() {
        return commentRepository.findAll()
                .stream().map(commentMapper::mapToDomain).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id, String userId) {
        final CommentJpaEntity entity = commentRepository.findById(id).orElseThrow();
        if (!Objects.equals(entity.getUserId(), userId)) throw new CustomApiException("not same user");
        entity.delete();
    }

    @Transactional
    public void update(String userId, String comment, Long id) {
        // TODO userID 체크하는 로직이 필요할 거 같다. 위에 delete 도.
        userRepository.findByUserId(userId).orElseThrow();

        CommentJpaEntity entity = commentRepository.findById(id).orElseThrow();
        entity.update(comment);
    }

    private void verifyUserId(String userId) {
        userRepository.findById(userId).orElseThrow();
    }

}
