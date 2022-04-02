package com.example.ecommerce.persistence.scrap;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScrapRepository extends JpaRepository<ScrapJpaEntity, Integer> {

	@Modifying
	@Query(value = "INSERT INTO scrap(event_id, user_id, created_time) VALUES(:eventId, :userId, now())", nativeQuery = true)
	void scrap(@Param("eventId") String eventId, @Param("userId") String userId);

	@Modifying
	@Query(value = "DELETE FROM scrap WHERE event_id = :eventId AND user_id = :userId", nativeQuery = true)
	void unscrap(@Param("eventId") String eventId, @Param("userId") String userId);

	@Query(value = "SELECT * FROM scrap WHERE user_id = :userId", nativeQuery = true)
	List<ScrapJpaEntity> retrieveScrap(@Param("userId") String userId);

}