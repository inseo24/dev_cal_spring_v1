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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.ImageDTO;
import com.example.ecommerce.model.BoardEntity;
import com.example.ecommerce.model.ImageEntity;
import com.example.ecommerce.persistence.BoardRepository;
import com.example.ecommerce.persistence.ImageRepository;


@Service
public class BoardService {

	@Autowired
	private BoardRepository repo;
	
	@Autowired
	private ImageRepository imgrepo;
	
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
		
		repo.save(entity);
		
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
		imgrepo.save(image);		

		return repo.findByBoardId(entity.getBoardId());
	}
	
	public List<BoardEntity> createBoard(final BoardEntity entity) {
		
		validate(entity);
		
		repo.save(entity);

		return repo.findByBoardId(entity.getBoardId());
	}
	
	public List<BoardEntity> retrieve(){
		return repo.findAll(Sort.by("boardId").descending());
	}
	
	public List<BoardEntity> retrieveItem(final String boardId){
		return repo.findByBoardId(boardId);
	}

	public List<BoardEntity> updateBoard(final BoardEntity entity, String boardId){
		
		validate(entity);
		
		final Optional<BoardEntity> original = repo.findById(boardId);
		
		original.ifPresent(board -> {
			board.setTitle(entity.getTitle());
			board.setContent(entity.getContent());
			board.setModified_date(LocalDateTime.now());
		
			repo.save(board);
		});
		
		return retrieve();
	}
	
	public List<BoardEntity> updateBoard(final BoardEntity entity, String boardId, ImageDTO imageDTO){
		
		validate(entity);
		
		final Optional<BoardEntity> original = repo.findById(boardId);
		
		ImageEntity img = imgrepo.findByBoardId(boardId);
		
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
			imgrepo.save(image);
			
		} else {
		
			img.setName(imageFileName);
			img.setType(type);
			
			imgrepo.save(img);
			
			
		}
		
		original.ifPresent(board -> {
			board.setTitle(entity.getTitle());
			board.setContent(entity.getContent());
			board.setModified_date(LocalDateTime.now());
		
			repo.save(board);
		});
		
		return retrieve();
	}
	
	public List<BoardEntity> deleteBoard(String boardId){
		
		try {
			
			repo.deleteById(boardId);
			
		} catch(Exception e) {
			
			throw new RuntimeException("error deleting entity" + boardId);
		}
		
		return CMResponseDTO(1, "삭제 성공", null);
	}


	private List<BoardEntity> CMResponseDTO(int i, String string, Object object) {
		return null;
	}
}
