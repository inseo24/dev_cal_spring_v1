package com.example.ecommerce.dto.user.response;

import com.example.ecommerce.domain.User;
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

	public User toEntity() {
		return User.builder()
				.name(name)
				.email(email)
				.mobileNumber(mobileNumber)
				.build();
	}
}
