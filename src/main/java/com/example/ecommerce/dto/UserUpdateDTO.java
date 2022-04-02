package com.example.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserUpdateDTO {
	
	private String name; 	
	private String email;

	@NotBlank
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}")
	private String password;

}
