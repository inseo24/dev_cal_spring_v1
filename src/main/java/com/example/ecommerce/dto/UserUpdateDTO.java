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

	@NotBlank(message="��й�ȣ�� �����Դϴ�.")	
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
    message = "��й�ȣ�� ���� ��,�ҹ��ڿ� ����, Ư����ȣ�� ��� 1�� �̻� ���Ե� 8�� ~ 20���� ��й�ȣ���� �մϴ�.")	
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
