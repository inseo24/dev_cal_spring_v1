package com.example.ecommerce.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.image.ImageDTO;
import com.example.ecommerce.persistence.board.BoardEntity;
import com.example.ecommerce.persistence.image.ImageEntity;
import com.example.ecommerce.persistence.board.BoardRepository;
import com.example.ecommerce.persistence.image.ImageRepository;


@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder;

	private void validate(final BoardEntity entity) {
		if (entity == null) {
			throw new RuntimeException("Entity cannot be null");
		}

		if (entity.getUserId() == null) {
			throw new RuntimeException("Unknown user.");
		}
	}
	
	
	public List<BoardEntity> createBoard(final BoardEntity entity, ImageDTO imageDTO) {
		
		validate(entity);
		
		boardRepository.save(entity);
		
		UUID uuid = UUID.randomUUID();
		
		String imageFileName = uuid + "_" + imageDTO.getFile().getOriginalFilename();
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		try {
			if (imageDTO.getFile().isEmpty()) {
				throw new Exception("Error: file is empty");
			}
			if (!Files.exists(imageFilePath)) {
				Files.write(imageFilePath, imageDTO.getFile().getBytes());
			}
			
			try (InputStream inputStream = imageDTO.getFile().getInputStream()){
				Files.copy(inputStream, imageFilePath.resolve(imageDTO.getFile().getOriginalFilename()),
														StandardCopyOption.REPLACE_EXISTING);	
			}
			
		} catch (Exception e) {
			
		}
		
		String type = imageDTO.getFile().getContentType();
		
		String boardId = entity.getBoardId();
		ImageEntity image = imageDTO.toEntity(type, imageFileName, boardId);
		imageRepository.save(image);

		return boardRepository.findByBoardId(entity.getBoardId());
	}
	
	public BoardEntity create(final BoardEntity entity) {
		
		validate(entity);
		
		BoardEntity savedEntity = boardRepository.save(entity);

		return savedEntity;
	}
	
	public List<BoardEntity> retrieve(){
		return boardRepository.findAll(Sort.by("boardId").descending());
	}
	
	public List<BoardEntity> retrieveItem(final String boardId){
		return boardRepository.findByBoardId(boardId);
	}

	public List<BoardEntity> updateBoard(final BoardEntity entity, String boardId){
		
		validate(entity);
		
		final Optional<BoardEntity> original = boardRepository.findById(boardId);
		
		original.ifPresent(board -> {
			board.setTitle(entity.getTitle());
			board.setContent(entity.getContent());
			board.setModified_date(LocalDateTime.now());
		
			boardRepository.save(board);
		});
		
		return retrieve();
	}
	
	public List<BoardEntity> updateBoard(final BoardEntity entity, String boardId, ImageDTO imageDTO){
		
		validate(entity);
		
		final Optional<BoardEntity> original = boardRepository.findById(boardId);
		
		ImageEntity img = imageRepository.findByBoardId(boardId);
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + imageDTO.getFile().getOriginalFilename();	
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		try {
			if (imageDTO.getFile().isEmpty()) {
				throw new Exception("Error: file is empty");
			}
			if (!Files.exists(imageFilePath)) {
				Files.write(imageFilePath, imageDTO.getFile().getBytes());
			}
			
			try (InputStream inputStream = imageDTO.getFile().getInputStream()){
				Files.copy(inputStream, imageFilePath.resolve(imageDTO.getFile().getOriginalFilename()),
														StandardCopyOption.REPLACE_EXISTING);	
			}
			
		} catch (Exception e) {
			
		}
		
		String type = imageDTO.getFile().getContentType();
		
		
		if (img == null) {
			ImageEntity image = new ImageEntity();
			image.setBoardId(boardId);
			image.setName(imageFileName);
			image.setType(type);
			imageRepository.save(image);
			
		} else {
		
			img.setName(imageFileName);
			img.setType(type);
			
			imageRepository.save(img);
			
			
		}
		
		original.ifPresent(board -> {
			board.setTitle(entity.getTitle());
			board.setContent(entity.getContent());
			board.setModified_date(LocalDateTime.now());
		
			boardRepository.save(board);
		});
		
		return retrieve();
	}
	
	public List<BoardEntity> deleteBoard(String boardId){
		
		try {
			
			boardRepository.deleteById(boardId);
			
		} catch(Exception e) {
			
			throw new RuntimeException("error deleting entity" + boardId);
		}
		
		return CMResponseDTO(1, "error", null);
	}


	private List<BoardEntity> CMResponseDTO(int i, String string, Object object) {
		return null;
	}
}
