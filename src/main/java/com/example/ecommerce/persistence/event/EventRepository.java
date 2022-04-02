package com.example.ecommerce.persistence.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, String>{

	List<EventEntity> findByEventId(String eventId);
	List<EventEntity> findByTitleContains(String title);

}
