package com.example.ecommerce.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.ecommerce.domain.User;
import lombok.*;

@Getter
@NoArgsConstructor
public class UserDTO {
	
	private String token;
	
 	@NotBlank
    @Email
 	private String email;
	
	@Size(min=2, max = 10)
	private String name;
	
	@NotBlank
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}")
	private String password;
	
	private String userId;
	
 	@Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
	private String mobileNumber;

	 @Builder
	public UserDTO(String token, User user) {
		this.token = token;
		this.email = user.getEmail();
		this.name = user.getName();
		this.password = user.getPassword();
		this.userId = user.getUserId();
		this.mobileNumber = user.getMobileNumber();
	}
}
