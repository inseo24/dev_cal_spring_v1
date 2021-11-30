package com.example.ecommerce.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, String>{

	List<EventEntity> findByEventId(String eventId);
	

}
