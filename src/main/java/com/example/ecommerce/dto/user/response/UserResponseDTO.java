package com.example.ecommerce.dto.user.response;

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
	private String mobileNumber;

	public UserEntity toEntity() {
		return UserEntity.builder()
				.name(name)
				.email(email)
				.mobileNumber(mobileNumber)
				.build();
	}
}
