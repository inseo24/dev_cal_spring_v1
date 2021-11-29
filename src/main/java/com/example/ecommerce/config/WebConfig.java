package com.example.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private final long MAX_AGE_SECS = 3600;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:3000") // 자원 공유 허락
		.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // 메서드 허락
		.allowedHeaders("*") // 
		.allowCredentials(true) 
		.maxAge(MAX_AGE_SECS); // 원하는 시간만큼 pre-flight 리퀘스트 캐싱
	}
	
}
