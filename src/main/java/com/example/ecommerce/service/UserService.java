package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		// matches �޼��带 �̿��� �н����尡 ������ Ȯ��
		if(originalUser != null && 
				encoder.matches(password, 
				originalUser.getPassword())){
			return originalUser;
		}
		
		return null;
	}
	
	
	
}
