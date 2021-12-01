package com.example.ecommerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
	
	private String token;
	
 	@NotBlank(message="이메일이 공백입니다.")	
    @Email(message = "이메일 형식에 맞지 않습니다.")
 	private String email;
	
	@NotBlank(message="이름이 공백입니다.")
	@Size(min=2, max = 10, message = "이름은 2자 이상 10자 이하여야 합니다." )
	private String name;
	
	@NotBlank(message="비밀번호가 공백입니다.")	
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
    message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")	
	private String password;
	
	private String userId;
	
 	@NotBlank(message="휴대폰 번호가 공백입니다.")
 	@Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "휴대폰 번호는 10 ~ 11 자리의 숫자만 입력 가능합니다.")
	private String mobileNum;
	

}
