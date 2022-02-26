package com.weatherapp.persistence;

import com.weatherapp.persistence.entity.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {
    List<Weather> findAll();

}
