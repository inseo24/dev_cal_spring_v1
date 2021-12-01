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
	
 	@NotBlank(message="�̸����� �����Դϴ�.")	
    @Email(message = "�̸��� ���Ŀ� ���� �ʽ��ϴ�.")
 	private String email;
	
	@NotBlank(message="�̸��� �����Դϴ�.")
	@Size(min=2, max = 10, message = "�̸��� 2�� �̻� 10�� ���Ͽ��� �մϴ�." )
	private String name;
	
	@NotBlank(message="��й�ȣ�� �����Դϴ�.")	
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
    message = "��й�ȣ�� ���� ��,�ҹ��ڿ� ����, Ư����ȣ�� ��� 1�� �̻� ���Ե� 8�� ~ 20���� ��й�ȣ���� �մϴ�.")	
	private String password;
	
	private String userId;
	
 	@NotBlank(message="�޴��� ��ȣ�� �����Դϴ�.")
 	@Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "�޴��� ��ȣ�� 10 ~ 11 �ڸ��� ���ڸ� �Է� �����մϴ�.")
	private String mobileNum;
	

}
