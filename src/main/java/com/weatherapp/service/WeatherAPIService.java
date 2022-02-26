package com.weatherapp.service;

import com.weatherapp.persistence.entity.Weather;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Locale;

@Service
public class WeatherAPIService {

    private final WebClient webClient;

    public WeatherAPIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org/data/2.5").build();
    }

    private JSONObject weatherAPICall (String lat, String lon) {
        String apiKey = "YOUR_API_KEY";
        String responseJson = webClient.get().uri("/onecall?lat={lat}&lon={lon}&units=metric&appid={apiKey}", lat, lon, apiKey)
                .retrieve().bodyToMono(String.class).block();

        return new JSONObject(responseJson);
    }


    public Weather getWeatherData(Weather weather) {

        JSONObject response = weatherAPICall(weather.getLat(), weather.getLon());
        JSONObject current = response.getJSONObject("current");
        JSONObject daily = response.getJSONArray("daily").getJSONObject(0);
        JSONObject curWeather = current.getJSONArray("weather").getJSONObject(0);
        System.out.println(daily);

        weather.setWeather(curWeather.getString("main"));
        String weatherDesc = curWeather.getString("description"); //I make this a separate variable just to make the code below
                                                                      //where I capitalize the first letter more readable.
        weather.setWeatherDesc(weatherDesc.substring(0, 1).toUpperCase(Locale.ROOT) + weatherDesc.substring(1));
        weather.setTemp(String.valueOf(Math.round(current.getFloat("temp"))));
        weather.setHumidity(String.valueOf(current.getInt("humidity")));
        weather.setWindSpeed(String.valueOf(current.getInt("wind_speed")));
        weather.setDailyPOP(String.valueOf(Math.round(daily.getFloat("pop")*100)));
        weather.setDailyClouds(String.valueOf(daily.getInt("clouds")));
        weather.setMinTemp(String.valueOf(daily.getJSONObject("temp").getFloat("min")));
        weather.setMaxTemp(String.valueOf(daily.getJSONObject("temp").getFloat("max")));



        return weather;
    }


}
