package com.example.ecommerce.service;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.mapper.UserMapper;
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
	private final UserMapper userMapper;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public void create(final User user) {
		verifyUniqueEmail(user.getEmail());
		if (user == null || user.getEmail() == null ) {
			throw new RuntimeException("Invalid arguments");
		}
		
		final String email = user.getEmail();
		
		if (userRepository.existsByEmail(email)) {
			throw new RuntimeException("Email already exists");
		}
	}

	public User getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
		final User originalUser = userMapper.mapToDomain(userRepository.findByEmail(email).orElseThrow());
		isPasswordMatches(password, encoder, originalUser);
		return originalUser;
	}

	@Transactional
	public void updatePassword(final String userId, final String password) {
		final User user = userMapper.mapToDomain(userRepository.findById(userId).get());
		user.updatePassword(password);
		userRepository.save(userMapper.mapToJpaEntity(user));
	}

	private void isPasswordMatches(String password, PasswordEncoder encoder, User originalUser) {
		if (!encoder.matches(password, originalUser.getPassword())) {
			throw new RuntimeException("user password not match");
		}
	}

	private void verifyUniqueEmail(String email) {
		if (userRepository.existsByEmail(email)) throw new RuntimeException("Email already exists");

	}
}
