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
		
		// 기한 오늘부터 1일
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
		// SECRET_KEY를 이용해 서명한 부분
		Nn4d1MOVLZg79sfFACTIpCPKqWmpZMZQsbNrXdJJNWkRv50_l7bPLQPwhMobT4vBOG6Q3JYjhDrKFlBSaUxZOg
		 */
		
		// Jwt 토큰 생성
		return Jwts.builder()
				// header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
				.signWith(SignatureAlgorithm.HS512, SECRETE_KEY)
				// payload에 들어갈 내요
				.setSubject(userEntity.getUserId()) // sub
				.setIssuer("서인이의 얼렁뚱땅 마켓") // iss
				.setIssuedAt(new Date()) // iat
				.setExpiration(expiryDate) // exp
				.compact();
		
				
	}
	
	public String validateAndGetUserId(String token) {
		// parseClaimsJws메서드가 Base 64로 디코딩 및 파싱.
		// 즉, 헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용 해 서명 후, token의 서명 과 비교.
		// 위조되지 않았다면 페이로드(Claims) 리턴
		// 그 중 userId가 필요하므로 getBody를 부름(subject가 사용자 아이디)
		
		Claims claims = Jwts.parser()
				.setSigningKey(SECRETE_KEY)
				.parseClaimsJws(token)
				.getBody();
		
		
		return claims.getSubject();
	}

}
