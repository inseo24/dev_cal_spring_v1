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

import com.example.ecommerce.dto.CMResponseDTO;
import com.example.ecommerce.dto.ImageDTO;
import com.example.ecommerce.model.BoardEntity;
import com.example.ecommerce.model.ImageEntity;
import com.example.ecommerce.model.UserEntity;
import com.example.ecommerce.persistence.BoardRepository;
import com.example.ecommerce.persistence.ImageRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {

	@Autowired
	private BoardRepository repo;
	
	@Autowired
	private ImageRepository imgrepo;
	
	@Value("${file.path}")
	private String uploadFolder;

	private void validate(final BoardEntity entity) {
		// validation
		if (entity == null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null");
		}

		if (entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
	
	
	// create with image
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
	
	// board create without img
	public List<BoardEntity> createBoard(final BoardEntity entity) {
		
		validate(entity);
		
		repo.save(entity);

		return repo.findByBoardId(entity.getBoardId());
	}
	
	// retrieve
	public List<BoardEntity> retrieve(){
		return repo.findAll(Sort.by("boardId").descending());
	}
	
	// retrieve
	public List<BoardEntity> retrieveItem(final String boardId){
		return repo.findByBoardId(boardId);
	}

	// update
	public List<BoardEntity> updateBoard(final BoardEntity entity, String boardId){
		
		validate(entity);
		
		// 넘겨받은 엔티티 id를 이용해 BoardEntity를 가져온다. 존재하지 않는 엔티티는 업데이트할 수 없으므로!
		final Optional<BoardEntity> original = repo.findById(boardId);
		
		original.ifPresent(board -> {
			// 반환된 엔티티가 존쟇면 값을 새 엔티티 값으로 덮어 씌움
			board.setTitle(entity.getTitle());
			board.setContent(entity.getContent());
			board.setModified_date(LocalDateTime.now());
		
			// db에 새로운 값을 저장
			repo.save(board);
		});
		
		// retrieve 메서드를 이용해 사용자의 모든 리스트를 리턴
		return retrieve();
	}
	
	// update
	public List<BoardEntity> updateBoard(final BoardEntity entity, String boardId, ImageDTO imageDTO){
		
		validate(entity);
		
		// 넘겨받은 엔티티 id를 이용해 BoardEntity를 가져온다. 존재하지 않는 엔티티는 업데이트할 수 없으므로!
		final Optional<BoardEntity> original = repo.findById(boardId);
		
		final Optional<ImageEntity> img = imgrepo.findByBoardId(boardId);
		
		System.out.println("img :" + img);
		
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
		
		img.ifPresent(image -> {
			image.setName(imageFileName);
			image.setType(type);
			
			imgrepo.save(image);
		});
		
		original.ifPresent(board -> {
			// 반환된 엔티티가 존재하면 값을 새 엔티티 값으로 덮어 씌움
			board.setTitle(entity.getTitle());
			board.setContent(entity.getContent());
			board.setModified_date(LocalDateTime.now());
		
			// db에 새로운 값을 저장
			repo.save(board);
		});
		
		// retrieve 메서드를 이용해 사용자의 모든 리스트를 리턴
		return retrieve();
	}
	
	// delete
	public List<BoardEntity> deleteBoard(String boardId){
		
		try {
			// 삭제
			
			repo.deleteById(boardId);
			
		} catch(Exception e) {
			// exception 발생 시 id와 exception을 로깅
			log.error("error deleting entity", boardId, e);
			
			// 컨트롤러로 exception 보내기
			// db 내부 로직을 캡슐화하기 위해 e를 리턴하지 않고 새 exception 오브젝트 리턴
			throw new RuntimeException("error deleting entity" + boardId);
		}
		
		// 새 list 반환
		return CMResponseDTO(1, "삭제 성공", null);
	}


	private List<BoardEntity> CMResponseDTO(int i, String string, Object object) {
		return null;
	}
}
