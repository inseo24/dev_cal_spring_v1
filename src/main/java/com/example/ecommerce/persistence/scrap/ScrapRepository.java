package com.example.ecommerce.persistence.scrap;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<ScrapJpaEntity, Long> {
	Optional<ScrapJpaEntity> findByUserIdAndEventId(Long eventId, String userId);
	List<ScrapJpaEntity> findAllByUserId(String userId);
}
