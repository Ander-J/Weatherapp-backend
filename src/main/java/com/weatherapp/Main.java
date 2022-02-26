package com.weatherapp;

import com.weatherapp.persistence.WeatherRepository;
import com.weatherapp.persistence.entity.Weather;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

@AllArgsConstructor
@SpringBootApplication
public class Main {

    private final WeatherRepository weatherRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDb(){
        Weather weather1 = new Weather().setId(1L).setLongName("Tallinn, Estonia").setLocation("Tallinn").setLat("59.435905").setLon("24.750486").setTemp("15").setWeather("Clear").setWeatherDesc("Sunny with light clouds");
        Weather weather2 = new Weather().setId(2L).setLocation("Tartu").setLongName("Tartu, Estonia").setLat("58.380169").setLon("26.722635").setTemp("11").setWeather("Rain").setWeatherDesc("Light rain and strong winds");
        Weather weather3 = new Weather().setId(3L).setLocation("Helsinki").setLongName("Helsinki, Finland").setLat("60.169812").setLon("24.938376").setTemp("12").setWeather("Clouds").setWeatherDesc("Heavy clouds with rain expected later");
        Weather weather4 = new Weather().setId(4L).setLocation("London").setLongName("London, United Kingdom").setLat("51.507271").setLon("0.127614").setTemp("7").setWeather("Rain").setWeatherDesc("Heavy rain");

        weatherRepository.saveAll(Arrays.asList(weather1, weather2, weather3, weather4));
    }

}
