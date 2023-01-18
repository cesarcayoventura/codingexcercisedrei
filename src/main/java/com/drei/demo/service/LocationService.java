package com.drei.demo.service;

import com.drei.demo.dto.LocationDto;
import com.drei.demo.dto.SearchLocation;
import com.drei.demo.persistence.Location;
import com.drei.demo.persistence.repository.LocationRepository;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public LocationDto createLocation(final LocationDto location) {
		Location locDb = new Location();
		locDb.setLat(location.getLat());
		locDb.setName(location.getName());
		locDb.setType(location.getType());
		locDb.setLng(location.getLng());

		var savedLocation = locationRepository.save(locDb);
		return location.withId(savedLocation.getId());
	}

	public List<LocationDto> searchLocation(Optional<SearchLocation> search) {
		if (search.isPresent()) {
			var criteria = search.get();
			var type = criteria.getType().orElse(null);
			var isCriteriaRectangular = criteria.getP1().isPresent() && criteria.getP2().isPresent();
			if (type != null && !isCriteriaRectangular) {
				
				var locations = locationRepository.findLocationsByType(type.name());
				return locations
						.stream()
						.limit(criteria.getLimit().orElse(locations.size()))
						.map(Location::toModel)
						.collect(Collectors.toList());
			}
			var p1 = criteria.getP1().orElse(null);
			var p2 = criteria.getP2().orElse(null);
			if (p1 != null && p2 != null) {
				Double x1 = p2.getValue0();
				Double x2 = p1.getValue0();
				Double y1 = p2.getValue1();
				Double y2 = p1.getValue1();
				if (p1.getValue0() < p2.getValue0()) {
					x1 = p1.getValue0();
					x2 = p2.getValue0();
				}
				if (p1.getValue1() < p2.getValue1()) {
					y1 = p1.getValue1();
					y2 = p2.getValue1();
				}
				var locations = locationRepository.findLocationsByRectangular(x1, x2, y1, y2);
				if (type != null) {
					return locations.stream()
							.filter(l -> type.equals(l.getType()))
							.limit(criteria.getLimit().orElse(locations.size()))
							.sorted(Comparator.comparing(Location::getType).reversed())
							.map(Location::toModel)
							.collect(Collectors.toList());
				}
				return locations.stream()
					.limit(criteria.getLimit().orElse(locations.size()))
					.map(Location::toModel)
					.collect(Collectors.toList());
			}

		}
		return Collections.emptyList();
	}
}
