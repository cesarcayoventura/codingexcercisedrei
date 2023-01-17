package com.drei.demo.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.drei.demo.DemoApplication;
import com.drei.demo.dto.AbstractLocation.Type;
import com.drei.demo.dto.Location;
import java.math.BigDecimal;

@SpringBootTest(classes = DemoApplication.class)
class LocationDtoTest {

	@Test
	void checkLocationDtoEmpty() {
		var location = Location.builder();
		assertThrows(IllegalStateException.class, () -> location.build());
	}
}
