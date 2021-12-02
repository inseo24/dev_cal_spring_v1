package com.example.ecommerce.dto;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.ecommerce.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateDTO {

 	
	private String email;

	@NotBlank(message="비밀번호가 공백입니다.")	
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
    message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")	
	private String password;
	
	private LocalDateTime modifiedTime;
	
	public UserEntity toEntity() {
		return UserEntity.builder()
				.email(email)
				.modifiedTime(modifiedTime)
				.password(password)
				.build();
	}
}
