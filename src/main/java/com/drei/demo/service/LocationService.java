package com.drei.demo.service;

import com.drei.demo.dto.Location;
import com.drei.demo.dto.SearchLocation;
import com.drei.demo.persistence.repository.LocationRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationService {

	private static final Logger LOG = LoggerFactory.getLogger(LocationService.class);

	@Autowired
	private LocationRepository locationRepository;

	public Location createLocation(final Location location) {
		com.drei.demo.persistence.Location locDb = new com.drei.demo.persistence.Location();
		locDb.setLat(location.getLat());
		locDb.setName(location.getName());
		locDb.setType(location.getType());
		locDb.setLng(location.getLng());
		
		var savedLocation = locationRepository.save(locDb);
		return location.withId(savedLocation.getId());
	}

	public List<Location> searchLocation(Optional<SearchLocation> search) {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}
}
