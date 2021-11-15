package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.ProductEntity;
import com.example.ecommerce.persistence.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;

	public String testService() {
		
		ProductEntity entity = ProductEntity.builder().name("First Product").build();
		repo.save(entity);
		ProductEntity savedEntity = repo.findById(entity.getId()).get();
		return savedEntity.getName();
	}
}
