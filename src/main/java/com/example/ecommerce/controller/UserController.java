package com.example.ecommerce.controller;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.dto.user.request.AuthUserRequestDto;
import com.example.ecommerce.dto.user.request.CreateUserDto;
import com.example.ecommerce.dto.user.request.UpdatePasswordDto;
import com.example.ecommerce.dto.user.response.AuthUserResponseDto;
import com.example.ecommerce.security.TokenProvider;
import com.example.ecommerce.service.UserService;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/auth/signup")
    public ResponseEntity<?> register(@RequestBody @Valid CreateUserDto userDTO) {
        userService.create(CreateUserDto.toDomain(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthUserRequestDto userDTO) {
        User user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword(), passwordEncoder);
        return ResponseEntity.ok().body(new AuthUserResponseDto(tokenProvider.create(user)));
    }

    @PutMapping("/auth/update")
    public ResponseEntity<HttpStatus> updatePassword(@AuthenticationPrincipal String userId,
                                                     @RequestBody @Valid UpdatePasswordDto userDTO) {
        userService.updatePassword(userId, passwordEncoder.encode(userDTO.getPassword()));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}