package com.example.ecommerce.controller;


import java.util.List;
import java.util.stream.Collectors;

import com.example.ecommerce.domain.Comment;
import com.example.ecommerce.dto.comment.request.CreateCommentDto;
import com.example.ecommerce.dto.comment.request.UpdateCommentDto;
import com.example.ecommerce.dto.comment.response.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.service.CommentService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @GetMapping
    public ResponseEntity<?> retrieve() {
        return ok().body(service.retrieve());
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> retrieveByBoardId(@PathVariable String boardId) {
        List<Comment> commentList = service.retrieveByBoardId(boardId);
        return ok().body(commentList.stream().map(CommentResponseDto::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal String userId, @RequestBody CreateCommentDto commentDTO) {
        service.create(commentDTO.getComment(), commentDTO.getBoardId(), userId);
        return ok().body(HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal String userId,
										   @RequestBody UpdateCommentDto commentDTO,
										   @PathVariable Long commentId) {
        service.update(userId, commentDTO.getComment(), commentId);
        return ok(HttpStatus.OK);
    }

    @PutMapping("/{commentId}/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal String userId, @PathVariable Long commentId) {
        service.delete(commentId, userId);
        return ok(HttpStatus.OK);
    }
}
