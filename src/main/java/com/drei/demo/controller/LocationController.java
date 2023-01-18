package com.drei.demo.controller;

import com.drei.demo.dto.LocationDto;
import com.drei.demo.dto.SearchLocation;
import com.drei.demo.service.LocationService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

	@Autowired
	LocationService service;

	@PostMapping(value = "/api/location", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public LocationDto createLocation(@RequestBody LocationDto location) {
		return service.createLocation(location);
	}

	@GetMapping(value = "/api/location/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LocationDto> searchLocation(@RequestParam Optional<SearchLocation> search) {
		return service.searchLocation(search);
	}
}
