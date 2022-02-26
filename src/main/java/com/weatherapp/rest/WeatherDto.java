package com.weatherapp.rest;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class WeatherDto {

    private Long id;
    @NotEmpty
    private String location;
    private String longName;
    @NotEmpty
    private String lat;
    @NotEmpty
    private String lon;
    @NotEmpty
    private String temp;
    @NotEmpty
    private String weather;
    @NotEmpty
    private String weatherDesc;
    @NotEmpty
    private String humidity;
    @NotEmpty
    private String windSpeed;
    @NotEmpty
    private String minTemp;
    @NotEmpty
    private String maxTemp;
    @NotEmpty
    private String dailyPOP; //probability of precipitation
    @NotEmpty
    private String dailyClouds;
}
