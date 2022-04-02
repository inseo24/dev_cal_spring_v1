package com.example.ecommerce.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.example.ecommerce.persistence.user.UserJpaEntity;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider {
	private static final String SECRETE_KEY = "NMA8JPctFuna59f5";
	
	public String create(UserJpaEntity userEntity) {
		
		Date expiryDate = Date.from(
				Instant.now()
				.plus(1, ChronoUnit.DAYS));
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS512, SECRETE_KEY)
				.setSubject(userEntity.getUserId()) 
				.setIssuer("seoin")
				.setIssuedAt(new Date()) 
				.setExpiration(expiryDate) 
				.compact();
	}
	
	public String validateAndGetUserId(String token) {
		
		Claims claims = Jwts.parser()
				.setSigningKey(SECRETE_KEY)
				.parseClaimsJws(token)
				.getBody();
		
		
		return claims.getSubject();
	}

}
