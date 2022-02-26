package com.weatherapp.service;

import com.weatherapp.persistence.entity.Weather;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GoogleAPIService {

    private final WebClient webClient;

    public GoogleAPIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://maps.googleapis.com/maps/api/geocode/").build();
    }

    public JSONObject locationAPICall (String location) {
        location = location.replace(",", "+");
        location = location.replace(" ", "");
        String key = "YOUR_API_KEY";
        String responseJson = webClient.get().uri("/json?address={location}&key={key}", location, key)
                .retrieve().bodyToMono(String.class).block();

        return new JSONObject(responseJson);
    }

    public Weather newWeather(String location){
        JSONObject response = locationAPICall(location);
        Weather weather = new Weather();

        JSONArray arr = response.getJSONArray("results");
        JSONObject obj = arr.getJSONObject(0);
        JSONArray comp = obj.getJSONArray("address_components");
        JSONObject loc = obj.getJSONObject("geometry").getJSONObject("location");

        weather.setLongName(obj.getString("formatted_address"));
        weather.setLat(String.valueOf(loc.getFloat("lat")));
        weather.setLon(String.valueOf(loc.getFloat("lng")));

        String shortName = "";
        System.out.println(arr);
        for (int i = 0; i < comp.length(); i++) {
            JSONArray types = comp.getJSONObject(i).getJSONArray("types");
            for (int j = 0; j < types.length(); j++) {
                String type = types.getString(j);
                if (shortName == "" && type.compareTo("administrative_area_level_1") == 0){
                    shortName = comp.getJSONObject(i).getString("long_name");
                }
                if (type.compareTo("locality") == 0){
                    shortName = comp.getJSONObject(i).getString("long_name");
                }
            }
        }

        if (shortName.compareTo("") == 0){
            shortName = location;
        }

        weather.setLocation(shortName);

        return weather;
    }
}
