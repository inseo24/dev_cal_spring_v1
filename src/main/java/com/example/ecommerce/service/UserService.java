package com.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.UserEntity;
import com.example.ecommerce.persistence.UserRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserEntity create(final UserEntity userEntity) {
		verifyUniqueEmail(userEntity.getEmail());
		return userRepository.save(userEntity);
	}

	public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
		final UserEntity originalUser = userRepository.findByEmail(email).orElseThrow();
		isPasswordMatches(password, encoder, originalUser);
		return originalUser;
	}

	@Transactional
	public void updatePassword(final String userId, final String password) {
		final UserEntity userEntity = userRepository.findById(userId).get();
		userEntity.updatePassword(password);
	}

	private void isPasswordMatches(String password, PasswordEncoder encoder, UserEntity originalUser) {
		if (!encoder.matches(password, originalUser.getPassword())) {
			throw new RuntimeException("user password not match");
		}
	}

	private void verifyUniqueEmail(String email) {
		if (userRepository.existsByEmail(email)) throw new RuntimeException("Email already exists");
	}

}
