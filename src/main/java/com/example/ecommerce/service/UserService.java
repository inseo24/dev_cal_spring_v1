package com.example.ecommerce.service;

import com.example.ecommerce.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import com.example.ecommerce.dto.UserUpdateDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.ecommerce.persistence.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public void create(final UserJpaEntity userEntity) {
		verifyUniqueEmail(userEntity.getEmail());
		if (userEntity == null || userEntity.getEmail() == null ) {
			throw new RuntimeException("Invalid arguments");
		}
		
		final String email = userEntity.getEmail();
		
		if (userRepository.existsByEmail(email)) {
			throw new RuntimeException("Email already exists");
		}
	}

	public UserJpaEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
		final UserJpaEntity originalUser = userRepository.findByEmail(email).orElseThrow();
		isPasswordMatches(password, encoder, originalUser);
		return originalUser;
	}

	@Transactional
	public void updatePassword(final String userId, final String password) {
		final UserJpaEntity userEntity = userRepository.findById(userId).get();
		userEntity.updatePassword(password);
	}

	private void isPasswordMatches(String password, PasswordEncoder encoder, UserJpaEntity originalUser) {
		if (!encoder.matches(password, originalUser.getPassword())) {
			throw new RuntimeException("user password not match");
		}
	}

	private void verifyUniqueEmail(String email) {
		if (userRepository.existsByEmail(email)) throw new RuntimeException("Email already exists");

	}

	@Transactional
	public void updatePassword(final String userId, final UserUpdateDTO userUpdateDto) {
		final UserJpaEntity userEntity = userRepository.findById(userId).get();
		userEntity.updatePassword(userUpdateDto.getPassword());
		userRepository.save(userEntity);
	}
}
