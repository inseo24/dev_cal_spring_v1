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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<?> createBoardImg(@AuthenticationPrincipal String userId, ImageDTO imageDTO, @RequestPart(value="data", required = false) BoardDTO dto) {
		
		
		try {
			
			// BoardEntity로 변환
			BoardEntity entity = BoardDTO.toEntity(dto);
			
			UserEntity userEntity = userRepo.findByUserId(userId);
			entity.setUserId(userEntity);
						
			// 서비스를 이용해 Board 엔티티 생성
			List<BoardEntity> entities = service.createBoard(entity, imageDTO);

			// 자바 스트림을 이용해 리턴된 엔티티 리스트를 BoardDTO 리스트로 반환
			List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

			// 변환된 BoardDTO 리스트를 이용해 ResponseDTO를 초기화
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

			// ResponseDTO를 리턴
			return ResponseEntity.ok().body(response);

		} catch (Exception e) {
		
			// 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createBoard(@AuthenticationPrincipal String userId, @RequestPart(value="data", required = false) BoardDTO dto) {
		
		try {

			// BoardEntity로 변환
			BoardEntity entity = BoardDTO.toEntity(dto);
			
			UserEntity userEntity = userRepo.findByUserId(userId);
			System.out.println(userEntity);
			entity.setUserId(userEntity);

			// 서비스를 이용해 Board 엔티티 생성
			List<BoardEntity> entities = service.createBoard(entity);

			// 자바 스트림을 이용해 리턴된 엔티티 리스트를 BoardDTO 리스트로 반환
			List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

			// 변환된 BoardDTO 리스트를 이용해 ResponseDTO를 초기화
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);
				
		
		} catch (Exception e) {
		
			// 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		
		}
	}

	@GetMapping
	public ResponseEntity<?> retrieveBoardList(@AuthenticationPrincipal String userId) {

		// 서비스 메서드의 retrieve() 메서드를 사용해 boardList를 가져옴
		List<BoardEntity> entities = service.retrieve();

		// 자바 스트림을 이용해 리턴된 엔티티 리스트를 BoardList로 변환
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		// 변환된 BoardDTO 리스트를 이용해 ResponseDTO를 초기화
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		// ResponseDTO 반환
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/{boardId}")
	public ResponseEntity<?> retrieveBoardItem(@PathVariable String boardId) {

		// 서비스 메서드의 retrieve() 메서드를 사용해 boardList를 가져옴
		List<BoardEntity> entities = service.retrieveItem(boardId);

		// 자바 스트림을 이용해 리턴된 엔티티 리스트를 BoardList로 변환
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		// 변환된 BoardDTO 리스트를 이용해 ResponseDTO를 초기화
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		// ResponseDTO 반환
		return ResponseEntity.ok().body(response);
	}


	@PutMapping("/{boardId}/image")
	public ResponseEntity<?> updateBoard(@AuthenticationPrincipal String userId, ImageDTO imageDTO, @RequestPart(value="data", required = false) BoardDTO dto, @PathVariable String boardId) {
		
		// dto를 entity로 변환
		BoardEntity entity = BoardDTO.toEntity(dto);
		
		UserEntity userEntity = userRepo.findByUserId(userId);

		entity.setUserId(userEntity);

		// 서비스를 이용해 entity를 업데이트
		List<BoardEntity> entities = service.updateBoard(entity, boardId, imageDTO);

		// 자바 스트림을 이용해 리턴된 엔티티 리스트를 BoardList로 변환
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		// 변환된 BoardDTO 리스트를 이용해 ResponseDTO를 초기화
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		// ResponseDTO 반환
		return ResponseEntity.ok().body(response);

	}
	
	@PutMapping("/{boardId}")
	public ResponseEntity<?> updateBoard(@AuthenticationPrincipal String userId, @RequestPart(value="data", required = false) BoardDTO dto, @PathVariable String boardId) {
		
		// dto를 entity로 변환
		BoardEntity entity = BoardDTO.toEntity(dto);
		
		UserEntity userEntity = userRepo.findByUserId(userId);

		entity.setUserId(userEntity);

		// 서비스를 이용해 entity를 업데이트
		List<BoardEntity> entities = service.updateBoard(entity, boardId);

		// 자바 스트림을 이용해 리턴된 엔티티 리스트를 BoardList로 변환
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		// 변환된 BoardDTO 리스트를 이용해 ResponseDTO를 초기화
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		// ResponseDTO 반환
		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<?> deleteBoard(@AuthenticationPrincipal String userId, @PathVariable String boardId) {
		try {

			service.deleteBoard(boardId);

			
			return (ResponseEntity<?>) ResponseEntity.ok();
			
		} catch (Exception e) {
			// 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
