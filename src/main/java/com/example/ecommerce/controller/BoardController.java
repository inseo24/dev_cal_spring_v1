package com.example.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.ecommerce.dto.board.request.CreateBoardDto;
import com.example.ecommerce.dto.image.request.CreateImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce.dto.board.BoardDTO;
import com.example.ecommerce.dto.image.ImageDTO;
import com.example.ecommerce.service.BoardService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/image")
    public ResponseEntity<?> createWithImage(@AuthenticationPrincipal String userId,
                                             CreateImageDto imageDTO,
                                             @RequestPart(value = "data", required = false) CreateBoardDto boardDTO) {
        boardService.createWithImage(CreateBoardDto.toDomain(boardDTO), CreateImageDto.toDomain(imageDTO));
        return ok().body(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BoardDTO request) {
        boardService.create(BoardDTO.toDomain(request));
        return ok().body(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> retrieve() {
        List<BoardDTO> response = boardService.retrieve().stream().map(BoardDTO::new).collect(Collectors.toList());
        return ok().body(response);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> retrieveById(@PathVariable Long boardId) {
        return ok().body(boardService.retrieveById(boardId));
    }

    @PutMapping("/{boardId}/image")
    public ResponseEntity<?> updateWithImage(@AuthenticationPrincipal String userId, ImageDTO imageDTO,
                                             @RequestPart(value = "data", required = false) BoardDTO boardDTO,
                                             @PathVariable Long boardId) {
        boardService.updateWithImage(BoardDTO.toDomain(boardDTO), boardId, ImageDTO.toDomain(imageDTO));
        return ok().body(HttpStatus.OK);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<?> update(@AuthenticationPrincipal String userId,
                                    @RequestPart(value = "data", required = false) BoardDTO boardDTO,
                                    @PathVariable Long boardId) {
        boardService.update(userId, BoardDTO.toDomain(boardDTO), boardId);
        return ok().body(HttpStatus.OK);

    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal String userId, @PathVariable Long boardId) {
        boardService.delete(boardId, userId);
        return ok().body(HttpStatus.OK);
    }
}
