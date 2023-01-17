package com.drei.demo.dto;

import java.util.Optional;

import org.immutables.value.Value;
import org.javatuples.Pair;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Style(typeAbstract = { "Abstract*" }, typeImmutable = "*")
@Value.Immutable
@JsonDeserialize(as = SearchLocation.class, builder = SearchLocation.Builder.class)
public interface AbstractSearchLocation {

	Optional<Pair<Long, Long>> getP1();

	Optional<Pair<Long, Long>> getP2();

	Optional<AbstractLocation.Type> getType();

	Optional<Integer> getLimit();

}
