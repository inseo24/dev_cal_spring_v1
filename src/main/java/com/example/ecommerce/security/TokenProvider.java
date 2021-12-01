package com.example.ecommerce.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.ecommerce.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
	private static final String SECRETE_KEY = "NMA8JPctFuna59f5";
	
	public String create(UserEntity userEntity) {
		
		// ���� ���ú��� 1��
		Date expiryDate = Date.from(
				Instant.now()
				.plus(1, ChronoUnit.DAYS));
				
		
		/*
		{ // header
		  "alg":"HS512"
		}.
		{ // payload
		  "sub":"40288093784915d201784916a40c0001",
		  "iss": "ecommerce",
		  "iat":1595733657,
		  "exp":1596597657
		}.
		// SECRET_KEY�� �̿��� ������ �κ�
		Nn4d1MOVLZg79sfFACTIpCPKqWmpZMZQsbNrXdJJNWkRv50_l7bPLQPwhMobT4vBOG6Q3JYjhDrKFlBSaUxZOg
		 */
		
		// Jwt ��ū ����
		return Jwts.builder()
				// header�� �� ���� �� ������ �ϱ� ���� SECRET_KEY
				.signWith(SignatureAlgorithm.HS512, SECRETE_KEY)
				// payload�� �� ����
				.setSubject(userEntity.getUserId()) // sub
				.setIssuer("�������� �󷷶׶� ����") // iss
				.setIssuedAt(new Date()) // iat
				.setExpiration(expiryDate) // exp
				.compact();
		
				
	}
	
	public String validateAndGetUserId(String token) {
		// parseClaimsJws�޼��尡 Base 64�� ���ڵ� �� �Ľ�.
		// ��, ����� ���̷ε带 setSigningKey�� �Ѿ�� ��ũ���� �̿� �� ���� ��, token�� ���� �� ��.
		// �������� �ʾҴٸ� ���̷ε�(Claims) ����
		// �� �� userId�� �ʿ��ϹǷ� getBody�� �θ�(subject�� ����� ���̵�)
		
		Claims claims = Jwts.parser()
				.setSigningKey(SECRETE_KEY)
				.parseClaimsJws(token)
				.getBody();
		
		
		return claims.getSubject();
	}

}
