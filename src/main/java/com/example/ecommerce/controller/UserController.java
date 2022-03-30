package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.dto.UserUpdateDTO;
import com.example.ecommerce.model.UserEntity;
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
                .mobileNum(userDTO.getMobileNum()).password(passwordEncoder.encode(userDTO.getPassword())).build();
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
        userService.updatePassword(userId, userDTO.getPassword());
        return ResponseEntity.ok(HttpStatus.OK);
    }

}