package com.weatherapp.rest;

import com.weatherapp.persistence.entity.Weather;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-21T18:14:09+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class WeatherDtoMapperImpl implements WeatherDtoMapper {

    @Override
    public WeatherDto map(Weather weather) {
        if ( weather == null ) {
            return null;
        }

        WeatherDto weatherDto = new WeatherDto();

        weatherDto.setId( weather.getId() );
        weatherDto.setLocation( weather.getLocation() );
        weatherDto.setLongName( weather.getLongName() );
        weatherDto.setLat( weather.getLat() );
        weatherDto.setLon( weather.getLon() );
        weatherDto.setTemp( weather.getTemp() );
        weatherDto.setWeather( weather.getWeather() );
        weatherDto.setWeatherDesc( weather.getWeatherDesc() );
        weatherDto.setHumidity( weather.getHumidity() );
        weatherDto.setWindSpeed( weather.getWindSpeed() );
        weatherDto.setMinTemp( weather.getMinTemp() );
        weatherDto.setMaxTemp( weather.getMaxTemp() );
        weatherDto.setDailyPOP( weather.getDailyPOP() );
        weatherDto.setDailyClouds( weather.getDailyClouds() );

        return weatherDto;
    }

    @Override
    public Weather map(WeatherDto weatherDto) {
        if ( weatherDto == null ) {
            return null;
        }

        Weather weather = new Weather();

        weather.setId( weatherDto.getId() );
        weather.setLocation( weatherDto.getLocation() );
        weather.setLongName( weatherDto.getLongName() );
        weather.setLat( weatherDto.getLat() );
        weather.setLon( weatherDto.getLon() );
        weather.setTemp( weatherDto.getTemp() );
        weather.setWeather( weatherDto.getWeather() );
        weather.setWeatherDesc( weatherDto.getWeatherDesc() );
        weather.setHumidity( weatherDto.getHumidity() );
        weather.setWindSpeed( weatherDto.getWindSpeed() );
        weather.setMinTemp( weatherDto.getMinTemp() );
        weather.setMaxTemp( weatherDto.getMaxTemp() );
        weather.setDailyPOP( weatherDto.getDailyPOP() );
        weather.setDailyClouds( weatherDto.getDailyClouds() );

        return weather;
    }
}
