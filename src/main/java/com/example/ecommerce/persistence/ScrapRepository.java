package com.example.ecommerce.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce.model.ScrapEntity;

public interface ScrapRepository extends JpaRepository<ScrapEntity, String>{
	
	@Modifying
	@Query(value = "INSERT INTO scrap(event_id, user_id, created_time) VALUES(:eventId, :userId, now())", nativeQuery = true)
	void scrap(@Param("eventId") String eventId,@Param("userId") String userId);
	
	@Modifying
	@Query(value = "DELETE scrap WHERE eventId = :eventId AND userID = :userId", nativeQuery = true)
	void unscrap(@Param("eventId") String eventId,@Param("userId") String userId);

}
