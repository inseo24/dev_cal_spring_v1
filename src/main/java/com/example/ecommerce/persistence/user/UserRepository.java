package com.example.ecommerce.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserJpaEntity, String> {
	Optional<UserJpaEntity> findByEmail(String email);
	Boolean existsByEmail(String email);
	Optional<UserJpaEntity> findByUserId(String userId);
}
