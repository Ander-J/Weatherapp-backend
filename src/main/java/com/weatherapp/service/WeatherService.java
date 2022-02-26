package com.weatherapp.service;

import com.weatherapp.persistence.entity.Weather;
import com.weatherapp.persistence.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final GoogleAPIService googleAPIService;
    private final WeatherAPIService weatherAPIService;

    public List<Weather> findAll(){
        return new ArrayList<>(weatherRepository.findAll());
    }

    public Weather findWeatherById(Long id) {
        return weatherRepository.findById(id).orElseThrow();
    }

    public List<Weather> updateAll(){
        List<Weather> weatherList = findAll();

        for (Weather weather: weatherList){
            Weather newWeather = weatherAPIService.getWeatherData(weather);
            saveWeather(newWeather);
        }
        List<Weather> updatedList = findAll();

        return updatedList;
    }


    public boolean deleteWeather(Long weatherId) {
        try {
            Weather weather = findWeatherById(weatherId);
            weatherRepository.delete(weather);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public Weather newWeather(String location){
        Weather weather = weatherAPIService.getWeatherData(googleAPIService.newWeather(location));


        return weather;
    }

}
