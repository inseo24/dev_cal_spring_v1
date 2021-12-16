package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CMResponseDTO;
import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.dto.UserUpdateDTO;
import com.example.ecommerce.handler.ex.CustomValidationException;
import com.example.ecommerce.model.UserEntity;
import com.example.ecommerce.persistence.UserRepository;
import com.example.ecommerce.security.TokenProvider;
import com.example.ecommerce.service.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TokenProvider tokenProvider;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	


	@PostMapping("/auth/signup")
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			Map<String, String> errorMap = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}

			throw new CustomValidationException("유효성 검사 실패", errorMap);
		} else {

			UserEntity user = UserEntity.builder().email(userDTO.getEmail()).name(userDTO.getName())
					.mobileNum(userDTO.getMobileNum()).password(passwordEncoder.encode(userDTO.getPassword())).build();

			UserEntity registeredUser = userService.create(user);
			UserDTO responseUserDTO = UserDTO.builder().email(registeredUser.getEmail())
					.userId(registeredUser.getUserId()).name(registeredUser.getName()).build();


			return ResponseEntity.ok(responseUserDTO);
		}

	}

	@PostMapping("/auth/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
		UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword(), passwordEncoder);

		if (user != null) {
			final String token = tokenProvider.create(user);
			final UserDTO responseUserDTO = UserDTO.builder().email(user.getEmail()).mobileNum(user.getMobileNum())
					.name(user.getName()).userId(user.getUserId()).token(token).build();


			return ResponseEntity.ok().body(responseUserDTO);
		} else {
			ResponseDTO responseDTO = ResponseDTO.builder().error("Login failed.").build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}

	@PutMapping("/auth/update")
	public CMResponseDTO<?> updatePassword(@AuthenticationPrincipal String userId,
			@RequestBody @Valid UserUpdateDTO userDTO, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			Map<String, String> errorMap = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}

			throw new CustomValidationException("유효성 검사 실패", errorMap);
		
		} else {
			
			UserEntity user = userService.update(userId, userDTO.toEntity());

			userRepo.save(user);

			return new CMResponseDTO<>(1, "비밀번호가 수정되었습니다.", user);
		}

	}

}