package com.example.ecommerce.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.FileDTO;
import com.example.ecommerce.model.FileEntity;
import com.example.ecommerce.persistence.FileRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepo;

	
	@Value("${file.path}")
	private String uploadFolder;
	
	
	public void uploadImage(@AuthenticationPrincipal String userId, FileDTO fileDto) {
		
		
		UUID uuid = UUID.randomUUID();
		
		String imageFileName = uuid + "_" + fileDto.getFile().getOriginalFilename();	
		log.info("imageFile " + imageFileName);
		
		log.info("fileDTO: " + fileDto);

		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		try {
			if (fileDto.getFile().isEmpty()) {
				throw new Exception("Error: file is empty");
			}
			if (!Files.exists(imageFilePath)) {
				Files.write(imageFilePath, fileDto.getFile().getBytes());
			}
			
			try (InputStream inputStream = fileDto.getFile().getInputStream()){
				Files.copy(inputStream, imageFilePath.resolve(fileDto.getFile().getOriginalFilename()), 
						StandardCopyOption.REPLACE_EXISTING);
			}
					
		} catch ( Exception e ) {
			
		}
		
		String type =  fileDto.getFile().getContentType();
		String boardId = fileDto.getBoardId();
		
		FileEntity image = fileDto.toEntity(imageFileName, type, boardId);
		FileEntity imageEntity = fileRepo.save(image);
		
		log.info("fileDto: " + fileDto);
		
		log.info("image Entity: " + imageEntity);

		
		
	}
	
	@Transactional
    public Long saveFile(FileDTO fileDto) {
        return fileRepo.save(fileDto.toEntity()).getFileId();
    }
	
}
