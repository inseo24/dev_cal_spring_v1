package com.example.ecommerce.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	Optional<UserEntity> findByEmail(String email);
	Boolean existsByEmail(String email);
	UserEntity findByUserId(String userId);
	
}
