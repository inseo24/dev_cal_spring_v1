package com.example.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.BoardDTO;
import com.example.ecommerce.dto.ImageDTO;
import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.model.BoardEntity;
import com.example.ecommerce.model.UserEntity;
import com.example.ecommerce.persistence.UserRepository;
import com.example.ecommerce.service.BoardService;

@RestController
@RequestMapping("board")
public class BoardController {

	@Autowired
	public BoardService service;

	@Autowired
	public UserRepository userRepo;

	@PostMapping("/image")
	public ResponseEntity<?> createBoardImg(@AuthenticationPrincipal String userId, ImageDTO imageDTO,
			@RequestPart(value = "data", required = false) BoardDTO dto) {

		try {

			BoardEntity entity = BoardDTO.toEntity(dto);

			UserEntity userEntity = userRepo.findByUserId(userId);
			entity.setUserId(userEntity);

			List<BoardEntity> entities = service.createBoard(entity, imageDTO);

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
			@RequestPart(value = "data", required = false) BoardDTO dto) {

		try {

			BoardEntity entity = BoardDTO.toEntity(dto);

			UserEntity userEntity = userRepo.findByUserId(userId);
			System.out.println(userEntity);
			entity.setUserId(userEntity);

			List<BoardEntity> entities = service.createBoard(entity);

			List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);

		} catch (Exception e) {

			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();

			return ResponseEntity.badRequest().body(response);

		}
	}

	@GetMapping
	public ResponseEntity<?> retrieveBoardList(@AuthenticationPrincipal String userId) {

		List<BoardEntity> entities = service.retrieve();

		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<?> retrieveBoardItem(@PathVariable String boardId) {

		List<BoardEntity> entities = service.retrieveItem(boardId);

		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);
	}

	@PutMapping("/{boardId}/image")
	public ResponseEntity<?> updateBoard(@AuthenticationPrincipal String userId, ImageDTO imageDTO,
			@RequestPart(value = "data", required = false) BoardDTO dto, @PathVariable String boardId) {

		BoardEntity entity = BoardDTO.toEntity(dto);

		UserEntity userEntity = userRepo.findByUserId(userId);

		entity.setUserId(userEntity);

		List<BoardEntity> entities = service.updateBoard(entity, boardId, imageDTO);

		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);

	}

	@PutMapping("/{boardId}")
	public ResponseEntity<?> updateBoard(@AuthenticationPrincipal String userId,
			@RequestPart(value = "data", required = false) BoardDTO dto, @PathVariable String boardId) {

		BoardEntity entity = BoardDTO.toEntity(dto);

		UserEntity userEntity = userRepo.findByUserId(userId);

		entity.setUserId(userEntity);

		List<BoardEntity> entities = service.updateBoard(entity, boardId);

		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<?> deleteBoard(@AuthenticationPrincipal String userId, @PathVariable String boardId) {
		try {

			service.deleteBoard(boardId);

			return (ResponseEntity<?>) ResponseEntity.ok();

		} catch (Exception e) {

			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
