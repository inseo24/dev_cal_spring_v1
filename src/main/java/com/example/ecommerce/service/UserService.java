package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserUpdateDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.UserEntity;
import com.example.ecommerce.persistence.UserRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserEntity create(final UserEntity userEntity) {
		
		
		if (userEntity == null || userEntity.getEmail() == null ) {
			throw new RuntimeException("Invalid arguments");
		}
		
		final String email = userEntity.getEmail();
		
		if (userRepository.existsByEmail(email)) {
			throw new RuntimeException("Email already exists");
		}
		
		
		return userRepository.save(userEntity);
	}
	
	
	public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
		
		final UserEntity originalUser = userRepository.findByEmail(email);

		if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
			return originalUser;
		}
		
		return null;
	}

	@Transactional
	public void updatePassword(final String userId, final UserUpdateDTO userUpdateDto) {
		final UserEntity userEntity = userRepository.findById(userId).get();
		userEntity.updatePassword(userUpdateDto.getPassword());
		userRepository.save(userEntity);
	}

}
