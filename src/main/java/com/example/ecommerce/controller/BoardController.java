package com.example.ecommerce.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.BoardDTO;
import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.model.BoardEntity;
import com.example.ecommerce.service.BoardService;

@RestController
@RequestMapping("board")
public class BoardController {

	@Autowired
	public BoardService service;

	@PostMapping
	public ResponseEntity<?> createBoard(@AuthenticationPrincipal String userId, @RequestBody BoardDTO dto) {
		
		
		try {
			
			// BoardEntity로 변환
			BoardEntity entity = BoardDTO.toEntity(dto);

			// id를 null로 초기화, 생성 당시에는 id가 없어야함
			entity.setUserId(null);

			// 시간 설정
			entity.setCreatedTime(new Date().getTime());
			entity.setModified_date(new Date().getTime());

			// 사용자 아이디 설정
			entity.setUserId(userId);

			// 서비스를 이용해 Board 엔티티 생성
			List<BoardEntity> entities = service.createBoard(entity);

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

	@GetMapping
	public ResponseEntity<?> retrieveBoardList(@AuthenticationPrincipal String userId) {

		// 서비스 메서드의 retrieve() 메서드를 사용해 boardList를 가져옴
		List<BoardEntity> entities = service.retrieve(userId);

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


	@PutMapping
	public ResponseEntity<?> updateBoard(@AuthenticationPrincipal String userId, @RequestBody BoardDTO dto) {
		
		// dto를 entity로 변환
		BoardEntity entity = BoardDTO.toEntity(dto);

		entity.setUserId(userId);

		// 서비스를 이용해 entity를 업데이트
		List<BoardEntity> entities = service.updateBoard(entity);

		// 자바 스트림을 이용해 리턴된 엔티티 리스트를 BoardList로 변환
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		// 변환된 BoardDTO 리스트를 이용해 ResponseDTO를 초기화
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		// ResponseDTO 반환
		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping
	public ResponseEntity<?> deleteBoard(@AuthenticationPrincipal String userId, @RequestBody BoardDTO dto) {
		try {

			// boardEntity로 변환
			BoardEntity entity = BoardDTO.toEntity(dto);

			entity.setUserId(userId);

			// 서비스를 이용해 삭제
			List<BoardEntity> entities = service.deleteBoard(entity);

			// 자바 스트림을 이용해 리턴된 엔티티 리스트를 BoardList로 변환
			List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

			// 변환된 BoardDTO 리스트를 이용해 ResponseDTO를 초기화
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

			// ResponseDTO 반환
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			// 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
