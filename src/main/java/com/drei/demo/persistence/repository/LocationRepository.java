package com.drei.demo.persistence.repository;

import com.drei.demo.persistence.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
