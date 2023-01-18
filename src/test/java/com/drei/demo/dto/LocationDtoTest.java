package com.drei.demo.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.drei.demo.dto.AbstractLocationDto.Type;
import com.drei.demo.dto.LocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.drei.demo.DemoApplication;
import java.math.BigDecimal;

@SpringBootTest(classes = DemoApplication.class)
class LocationDtoTest {

	@Test
	void checkLocationDtoEmpty() {
		var location = LocationDto.builder();
		assertThrows(IllegalStateException.class, () -> location.build());
	}
}
