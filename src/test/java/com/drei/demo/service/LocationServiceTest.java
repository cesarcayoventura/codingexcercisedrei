package com.drei.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.drei.demo.DemoApplication;
import com.drei.demo.dto.AbstractLocationDto.Type;
import com.drei.demo.persistence.Location;
import com.drei.demo.persistence.repository.LocationRepository;
import com.google.gson.Gson;
import com.drei.demo.dto.LocationDto;
import com.drei.demo.dto.SearchLocation;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest(classes = DemoApplication.class)
class LocationServiceTest {

	@Autowired
	private LocationService locationService;

	@Autowired
	private LocationRepository locationRepository;

	private Long id;

	@Test
	void createLocation() {
		try {
			LocationDto locationDto = locationService.createLocation(generateLocation(Type.STANDAR));

			assertThat(locationDto).isNotNull();
			assertThat(locationDto.getName()).isEqualTo("name Test");
			assertThat(locationDto.getId()).isPresent();

			id = locationDto.getId().orElse(null);
		} finally {
			Location toDelete = new Location();
			toDelete.setId(id);
			locationRepository.delete(toDelete);
		}
	}
	
	@Test
	void searchLocationTypePremium() {
		Optional<Long> idP = Optional.empty();
		Optional<Long> idS = Optional.empty();

		try {
			LocationDto locationDtoS = locationService.createLocation(generateLocation(Type.STANDAR));
			LocationDto locationDtoP = locationService.createLocation(generateLocation(Type.PREMIUM));

			Optional<SearchLocation> search = Optional.of(SearchLocation.builder().type(Type.PREMIUM).build());

			var response = locationRepository.findLocationsByType(Type.PREMIUM.name());
			
			//filter if in the response is the current created location
			var location = response.stream().filter(r -> "name Test".equals(r.getName()) 
					&& r.getId().equals(locationDtoP.getId().get()));
			assertThat(location).hasSize(1);

			idP = locationDtoS.getId();
			idS = locationDtoS.getId();
		} finally {
			idP.ifPresent(id -> {
				Location toDelete = new Location();
				toDelete.setId(id);
				locationRepository.delete(toDelete);
			});
			idS.ifPresent(id -> {
				Location toDelete = new Location();
				toDelete.setId(id);
				locationRepository.delete(toDelete);
			});
		}
	}
	
	@Test
	void searchLocationTypeStandar() {
		Optional<Long> idP = Optional.empty();
		Optional<Long> idS = Optional.empty();

		try {
			LocationDto locationDtoS = locationService.createLocation(generateLocation(Type.STANDAR));
			LocationDto locationDtoP = locationService.createLocation(generateLocation(Type.PREMIUM));

			Optional<SearchLocation> search = Optional.of(SearchLocation.builder().type(Type.STANDAR).build());

			var response = locationRepository.findLocationsByType(Type.STANDAR.name());
			
			//filter if in the response is the current created location
			var location = response.stream().filter(r -> "name Test".equals(r.getName()) 
					&& r.getId().equals(locationDtoS.getId().get()));
			assertThat(location).hasSize(1);

			idP = locationDtoS.getId();
			idS = locationDtoS.getId();
		} finally {
			idP.ifPresent(id -> {
				Location toDelete = new Location();
				toDelete.setId(id);
				locationRepository.delete(toDelete);
			});
			idS.ifPresent(id -> {
				Location toDelete = new Location();
				toDelete.setId(id);
				locationRepository.delete(toDelete);
			});
		}
	}

	private LocationDto generateLocation(Type type) {
		return LocationDto.builder()
				.name("name Test")
				.lat(BigDecimal.valueOf(12.6))
				.lng(BigDecimal.valueOf(10.8))
				.type(type).build();
	}

}
