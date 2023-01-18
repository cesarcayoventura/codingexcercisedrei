package com.drei.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Optional;
import java.math.BigDecimal;
import org.immutables.value.Value;

@Value.Style(typeAbstract = { "Abstract*" }, typeImmutable = "*")
@Value.Immutable
@JsonDeserialize(as = LocationDto.class, builder = LocationDto.Builder.class)
public interface AbstractLocationDto {

	public enum Type {

		PREMIUM("premium"), STANDAR("standar");

		private final String value;

		private Type(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	Optional<Long> getId();

	String getName();

	Type getType();

	BigDecimal getLng();

	BigDecimal getLat();
}
