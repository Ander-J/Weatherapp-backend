package com.weatherapp.persistence.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "location")
    private String location;
    @Column(name = "longName")
    private String longName;
    @Column(name = "lat")
    private String lat;
    @Column(name = "lon")
    private String lon;
    @Column(name = "temp")
    private String temp;
    @Column(name = "weather")
    private String weather;
    @Column(name = "weatherDesc")
    private String weatherDesc;
    private String humidity;
    private String windSpeed;
    private String minTemp;
    private String maxTemp;
    private String dailyPOP; //probability of precipitation
    private String dailyClouds;


}
