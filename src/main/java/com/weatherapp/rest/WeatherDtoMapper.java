package com.weatherapp.rest;

import com.weatherapp.persistence.entity.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WeatherDtoMapper {

    WeatherDtoMapper INSTANCE = Mappers.getMapper(WeatherDtoMapper.class);

    WeatherDto map(Weather weather);
    Weather map(WeatherDto weatherDto);
}
