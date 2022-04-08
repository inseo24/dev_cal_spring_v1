package com.example.ecommerce.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.ecommerce.domain.Board;
import com.example.ecommerce.domain.Image;
import com.example.ecommerce.mapper.BoardMapper;
import com.example.ecommerce.mapper.ImageMapper;
import com.example.ecommerce.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ecommerce.persistence.board.BoardJpaEntity;
import com.example.ecommerce.persistence.image.ImageJpaEntity;
import com.example.ecommerce.persistence.board.BoardRepository;
import com.example.ecommerce.persistence.image.ImageRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final BoardMapper boardMapper;
    private final ImageMapper imageMapper;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void createWithImage(final Board board, Image image) {
        validate(board);
        verifyUserIdExists(board.getUserId());

        String imageFileName = UUID.randomUUID() + "_" + image.getFile().getOriginalFilename();
        imageFilePath(image, imageFileName);

        BoardJpaEntity boardJpaEntity = boardRepository.save(boardMapper.mapToJpaEntity(board));

        image.setBoardId(board.getBoardId());
        imageRepository.save(imageMapper.mapToJpaEntity(image));
    }

    @Transactional
    public void create(final Board board) {
        validate(board);
        verifyUserIdExists(board.getUserId());
        boardRepository.save(boardMapper.mapToJpaEntity(board));
    }

    @Transactional(readOnly = true)
    public List<Board> retrieve() {
        return boardRepository.findAll(Sort.by("boardId").descending())
                .stream().map(boardMapper::mapToDomain).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Board retrieveById(final Long boardId) {
        return boardMapper.mapToDomain(boardRepository.findByBoardId(boardId).orElseThrow());
    }

    @Transactional
    public void update(final String userId, final Board board, Long boardId) {
        validate(board);
        verifyBoardIdExists(boardId);
        verifyUserIdExists(board.getUserId());

        BoardJpaEntity entity = boardRepository.findByBoardIdAndUserId(boardId, userId).orElseThrow();
        boardRepository.save(entity);
    }

    @Transactional
    public void updateWithImage(final Board board, Long boardId, Image image) {
        validate(board);
        BoardJpaEntity boardJpaEntity = boardRepository.findByBoardIdAndUserId(boardId, board.getUserId()).orElseThrow();
        Board originalBoard = boardMapper.mapToDomain(boardJpaEntity);
        originalBoard.update(board.getTitle(), board.getContent());

        String imageFileName = UUID.randomUUID() + "_" + image.getFile().getOriginalFilename();
        String type = imageFilePath(image, imageFileName);

        ImageJpaEntity imageJpaEntity = imageRepository.findByBoardId(boardId).orElseThrow();
        Image originalImage = imageMapper.mapToDomain(imageJpaEntity);

        originalImage.setType(type);
        originalImage.setName(imageFileName);

        imageRepository.save(imageMapper.mapToJpaEntity(originalImage));
        boardRepository.save(boardMapper.mapToJpaEntity(originalBoard));
    }

    @Transactional
    public void delete(Long boardId, String userId) {
        BoardJpaEntity entity = boardRepository.findByBoardIdAndUserId(boardId, userId).orElseThrow();
        boardRepository.deleteById(entity.getBoardId());
    }

    private void verifyBoardIdExists(Long boardId) {
        boardRepository.findById(boardId).orElseThrow();
    }

    private void verifyUserIdExists(String userId) {
        userRepository.findByUserId(userId).orElseThrow();
    }

    private void validate(final Board board) {
        if (board == null) throw new RuntimeException("Entity cannot be null");
        if (board.getUserId() == null) throw new RuntimeException("user cannot be null");
    }


    // TODO 이거 언젠간 수정...
    private String imageFilePath(Image image, String imageFileName) {
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
        try {
            if (image.getFile().isEmpty()) {
                throw new Exception("Error: file is empty");
            }
            if (!Files.exists(imageFilePath)) {
                Files.write(imageFilePath, image.getFile().getBytes());
            }

            try (InputStream inputStream = image.getFile().getInputStream()) {
                Files.copy(inputStream, imageFilePath.resolve(image.getFile().getOriginalFilename()),
                        StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception e) {

        }
        return image.getFile().getOriginalFilename();
    }

}
