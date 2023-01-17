package com.drei.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.drei.demo.DemoApplication;
import com.drei.demo.dto.AbstractLocation.Type;
import com.drei.demo.persistence.repository.LocationRepository;
import com.drei.demo.dto.Location;
import java.math.BigDecimal;

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
			Location locationDb = locationService.createLocation(generateLocation());

			assertThat(locationDb).isNotNull();
			assertThat(locationDb.getName()).isEqualTo("name Test");
			assertThat(locationDb.getId()).isPresent();

			id = locationDb.getId().orElse(null);
		} finally {
			com.drei.demo.persistence.Location toDelete = new com.drei.demo.persistence.Location();
			toDelete.setId(id);
			locationRepository.delete(toDelete);
		}
	}

	private Location generateLocation() {
		return Location.builder().name("name Test").lat(BigDecimal.valueOf(12.6)).lng(BigDecimal.valueOf(10.8))
				.type(Type.PREMIUM).build();
	}

}
