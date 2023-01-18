package com.drei.demo.persistence;

import com.drei.demo.dto.AbstractLocationDto.Type;
import com.drei.demo.dto.LocationDto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LAT")
	private BigDecimal lat;

	@Column(name = "LNG")
	private BigDecimal lng;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	private Type type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, lat, lng, name, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Location other = (Location) obj;
		return Objects.equals(id, other.id) && Objects.equals(lat, other.lat) && Objects.equals(lng, other.lng)
				&& Objects.equals(name, other.name) && type == other.type;
	}
	
	public LocationDto toModel() {
		return LocationDto.builder()
				.id(Optional.of(getId()))
				.name(getName())
				.lat(getLat())
				.lng(getLng())
				.type(getType())
				.build();
	}
}
