package com.weatherapp.rest;

import com.weatherapp.persistence.entity.Weather;
import com.weatherapp.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("api/weatherapp/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherDtoMapper weatherDtoMapper;

    @GetMapping
    public ResponseEntity<List<WeatherDto>> getAll(){
        return ResponseEntity.ok(weatherService.findAll().stream().map(weatherDtoMapper::map).collect(Collectors.toList()));
    }

    @GetMapping("{weatherId}")
    public ResponseEntity<WeatherDto> getWeatherById(@PathVariable Long weatherId) {
        return ResponseEntity.ok(weatherDtoMapper.map(weatherService.findWeatherById(weatherId)));
    }

    @GetMapping("/update")
    public ResponseEntity<List<WeatherDto>> updateData(){
        return ResponseEntity.ok(weatherService.updateAll().stream().map(weatherDtoMapper::map).collect(Collectors.toList()));
    }

    @PostMapping("/new")
    public ResponseEntity<WeatherDto> createWeather(@RequestBody @Valid String location){
        Weather weather = weatherService.newWeather(location);

        weatherService.saveWeather(weather);


        return new ResponseEntity<>(weatherDtoMapper.map(weather), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{weatherId}")
    public ResponseEntity<Long> deleteWeather(@PathVariable Long weatherId){
        boolean isRemoved = weatherService.deleteWeather(weatherId);

        if (isRemoved){
            return new ResponseEntity<>(weatherId, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex){

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }


}
