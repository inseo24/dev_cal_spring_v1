package com.example.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce.dto.board.BoardDTO;
import com.example.ecommerce.dto.image.ImageDTO;
import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.persistence.board.BoardEntity;
import com.example.ecommerce.persistence.user.UserEntity;
import com.example.ecommerce.persistence.user.UserRepository;
import com.example.ecommerce.service.BoardService;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final UserRepository userRepository;

	@PostMapping("/image")
	public ResponseEntity<?> createBoardImg(@AuthenticationPrincipal String userId, ImageDTO imageDTO,
			@RequestPart(value = "data", required = false) BoardDTO dto) {

		try {

			BoardEntity entity = BoardDTO.toEntity(dto);

			UserEntity userEntity = userRepository.findByUserId(userId);
			entity.setUserId(userEntity);

			List<BoardEntity> entities = boardService.createBoard(entity, imageDTO);

			List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);

		} catch (Exception e) {

			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();

			return ResponseEntity.badRequest().body(response);

		}
	}

	@PostMapping
	public ResponseEntity<?> createBoard(@AuthenticationPrincipal String userId,
			@RequestBody BoardDTO dto) {

		try {
			UserEntity userEntity = userRepository.findByUserId(userId);
			BoardEntity entity = BoardDTO.toEntity(dto);
			entity.setUserId(userEntity);

			BoardEntity savedEntity = boardService.create(entity);

			return ResponseEntity.ok().body(savedEntity);

		} catch (Exception e) {

			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();

			return ResponseEntity.badRequest().body(response);

		}
	}

	@GetMapping
	public ResponseEntity<?> retrieveBoardList(@AuthenticationPrincipal String userId) {

		List<BoardEntity> entities = boardService.retrieve();

		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<?> retrieveBoardItem(@PathVariable String boardId) {

		List<BoardEntity> entities = boardService.retrieveItem(boardId);

		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);
	}

	@PutMapping("/{boardId}/image")
	public ResponseEntity<?> updateBoard(@AuthenticationPrincipal String userId, ImageDTO imageDTO,
			@RequestPart(value = "data", required = false) BoardDTO dto, @PathVariable String boardId) {

		BoardEntity entity = BoardDTO.toEntity(dto);

		UserEntity userEntity = userRepository.findByUserId(userId);

		entity.setUserId(userEntity);

		List<BoardEntity> entities = boardService.updateBoard(entity, boardId, imageDTO);

		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);

	}

	@PutMapping("/{boardId}")
	public ResponseEntity<?> updateBoard(@AuthenticationPrincipal String userId,
			@RequestPart(value = "data", required = false) BoardDTO dto, @PathVariable String boardId) {

		BoardEntity entity = BoardDTO.toEntity(dto);

		UserEntity userEntity = userRepository.findByUserId(userId);

		entity.setUserId(userEntity);

		List<BoardEntity> entities = boardService.updateBoard(entity, boardId);

		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<?> deleteBoard(@AuthenticationPrincipal String userId, @PathVariable String boardId) {
		try {

			boardService.deleteBoard(boardId);

			return (ResponseEntity<?>) ResponseEntity.ok();

		} catch (Exception e) {

			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
