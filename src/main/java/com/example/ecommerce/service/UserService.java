package com.example.ecommerce.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.UserEntity;
import com.example.ecommerce.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserEntity create(final UserEntity userEntity) {
		
		
		if (userEntity == null || userEntity.getEmail() == null ) {
			throw new RuntimeException("Invalid arguments");
		}
		
		final String email = userEntity.getEmail();
		
		if (userRepo.existsByEmail(email)) {
			log.warn("Email already exists {}", email);
			throw new RuntimeException("Email already exists");
		}
		
		
		return userRepo.save(userEntity);
	}
	
	
	public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
		
		final UserEntity originalUser = userRepo.findByEmail(email);

		// matches 메서드를 이용해 패스워드가 같은지 확인
		if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
			return originalUser;
		}
		
		return null;
	}
	
	
	public UserEntity update( final String userId, final UserEntity user) {
		
		final Optional<UserEntity> optUserEntity = userRepo.findById(userId);
		
		UserEntity userEntity = optUserEntity.get();
		
		log.info("user: " + userEntity);
		
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
		userEntity.setModifiedTime(LocalDateTime.now());

		return userEntity;
		
	}
	
	
}
