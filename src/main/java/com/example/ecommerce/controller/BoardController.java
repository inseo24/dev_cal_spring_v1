package com.example.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.ecommerce.dto.board.request.CreateBoardDto;
import com.example.ecommerce.dto.board.request.UpdateBoardDto;
import com.example.ecommerce.dto.board.response.BoardResponseDto;
import com.example.ecommerce.dto.image.request.CreateImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce.dto.image.ImageDTO;
import com.example.ecommerce.service.BoardService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // TODO ResponseEntity<?> <- 여기 이렇게 물음표 쓰는 방식 정말 좋은 걸까? 다른 문제는 없을까?
    @PostMapping("/image")
    public ResponseEntity<?> createWithImage(@AuthenticationPrincipal String userId,
                                             CreateImageDto imageDTO,
                                             @RequestPart(value = "data", required = false) CreateBoardDto boardDTO) {
        boardService.createWithImage(userId, CreateBoardDto.toDomain(boardDTO), CreateImageDto.toDomain(imageDTO));
        return ok().body(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateBoardDto request) {
        boardService.create(CreateBoardDto.toDomain(request));
        return ok().body(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> retrieve() {
        List<BoardResponseDto> response = boardService.retrieve().stream().map(BoardResponseDto::new).collect(Collectors.toList());
        return ok().body(response);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> retrieveById(@PathVariable Long boardId) {
        return ok().body(BoardResponseDto.builder().board(boardService.retrieveById(boardId)).build());
    }

    @PutMapping("/board/image/update")
    public ResponseEntity<?> updateWithImage(@AuthenticationPrincipal String userId,
                                             @RequestPart(value = "data", required = false) UpdateBoardDto boardDTO,
                                             @RequestPart ImageDTO imageDTO) {
        boardService.updateWithImage(userId, UpdateBoardDto.toDomain(boardDTO), ImageDTO.toDomain(imageDTO));
        return ok().body(HttpStatus.OK);
    }

    @PutMapping("/board/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal String userId,
                                    @RequestPart(value = "data", required = false) UpdateBoardDto boardDTO) {
        boardService.update(userId, UpdateBoardDto.toDomain(boardDTO));
        return ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal String userId, @PathVariable Long boardId) {
        boardService.delete(userId, boardId);
        return ok().body(HttpStatus.OK);
    }
}
