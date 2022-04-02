package com.example.ecommerce.persistence.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventJpaEntity, Long>{
	List<EventJpaEntity> findByEventId(String eventId);
	List<EventJpaEntity> findByTitleContains(String title);
}
