package com.example.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
			
			// BoardEntity�� ��ȯ
			BoardEntity entity = BoardDTO.toEntity(dto);

			// id�� null�� �ʱ�ȭ, ���� ��ÿ��� id�� �������
			entity.setUserId(null);

			// �ð� ����
			entity.setCreatedTime(new Date().getTime());
			entity.setModified_date(new Date().getTime());

			// ����� ���̵� ����
			entity.setUserId(userId);

			// ���񽺸� �̿��� Board ��ƼƼ ����
			List<BoardEntity> entities = service.createBoard(entity);

			// �ڹ� ��Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� BoardDTO ����Ʈ�� ��ȯ
			List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

			// ��ȯ�� BoardDTO ����Ʈ�� �̿��� ResponseDTO�� �ʱ�ȭ
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

			// ResponseDTO�� ����
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			// ���ܰ� �ִ� ��� dto ��� error�� �޽����� �־� ����
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping
	public ResponseEntity<?> retrieveBoardList(@AuthenticationPrincipal String userId) {

		// ���� �޼����� retrieve() �޼��带 ����� boardList�� ������
		List<BoardEntity> entities = service.retrieve(userId);

		// �ڹ� ��Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� BoardList�� ��ȯ
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		// ��ȯ�� BoardDTO ����Ʈ�� �̿��� ResponseDTO�� �ʱ�ȭ
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		// ResponseDTO ��ȯ
		return ResponseEntity.ok().body(response);
	}

	@PutMapping
	public ResponseEntity<?> updateBoard(@AuthenticationPrincipal String userId, @RequestBody BoardDTO dto) {
		
		// dto�� entity�� ��ȯ
		BoardEntity entity = BoardDTO.toEntity(dto);

		entity.setUserId(userId);

		// ���񽺸� �̿��� entity�� ������Ʈ
		List<BoardEntity> entities = service.updateBoard(entity);

		// �ڹ� ��Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� BoardList�� ��ȯ
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

		// ��ȯ�� BoardDTO ����Ʈ�� �̿��� ResponseDTO�� �ʱ�ȭ
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

		// ResponseDTO ��ȯ
		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping
	public ResponseEntity<?> deleteBoard(@AuthenticationPrincipal String userId, @RequestBody BoardDTO dto) {
		try {

			// boardEntity�� ��ȯ
			BoardEntity entity = BoardDTO.toEntity(dto);

			entity.setUserId(userId);

			// ���񽺸� �̿��� ����
			List<BoardEntity> entities = service.deleteBoard(entity);

			// �ڹ� ��Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� BoardList�� ��ȯ
			List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

			// ��ȯ�� BoardDTO ����Ʈ�� �̿��� ResponseDTO�� �ʱ�ȭ
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

			// ResponseDTO ��ȯ
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			// ���ܰ� �ִ� ��� dto ��� error�� �޽����� �־� ����
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
