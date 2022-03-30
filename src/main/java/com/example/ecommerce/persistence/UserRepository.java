package com.example.ecommerce.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	Optional<UserEntity> findByEmail(String email);
	Boolean existsByEmail(String email);
	UserEntity findByUserId(String userId);
	
}
