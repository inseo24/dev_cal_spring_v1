package com.example.ecommerce.dto;

import java.time.LocalDateTime;

import com.example.ecommerce.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDTO {
	private String token;
 	private String email;
	private String name;
	private String userId;
	private LocalDateTime modifiedTime;
	
	public UserEntity toEntity() {
		return UserEntity.builder()
				.name(name)
				.email(email)
				.modifiedTime(modifiedTime)
				.userId(userId)
				.build();
	}

}
