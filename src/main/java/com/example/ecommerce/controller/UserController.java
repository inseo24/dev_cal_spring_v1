package com.example.ecommerce.controller;

import com.example.ecommerce.dto.user.UserDTO;
import com.example.ecommerce.dto.user.request.UserUpdateDTO;
import com.example.ecommerce.persistence.user.UserEntity;
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
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDTO) {
        UserEntity user = UserEntity.builder().email(userDTO.getEmail()).name(userDTO.getName())
                .mobileNumber(userDTO.getMobileNumber()).password(passwordEncoder.encode(userDTO.getPassword())).build();
        userService.create(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticate(@RequestBody @Valid UserDTO userDTO) {
        UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword(), passwordEncoder);
        return ResponseEntity.ok().body(UserDTO.builder().token(tokenProvider.create(user)).build());
    }

    @PutMapping("/auth/update")
    public ResponseEntity<HttpStatus> updatePassword(@AuthenticationPrincipal String userId,
                                                     @RequestBody @Valid UserUpdateDTO userDTO) {
        userService.updatePassword(userId, passwordEncoder.encode(userDTO.getPassword()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

}