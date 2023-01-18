package com.drei.demo.persistence.repository;

import com.drei.demo.persistence.Location;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long> {

	@Query(value = "SELECT * FROM Location l WHERE l.lat >= ?1 and l.lat <= ?2 and l.lng >= ?3 and l.lng <= ?4", nativeQuery = true)
	List<Location> findLocationsByRectangular(Double p1x, Double p2x, Double p1y, Double p2y);

	@Query(value = "SELECT * FROM Location l WHERE l.type = ?1", nativeQuery = true)
	List<Location> findLocationsByType(String type);
}
