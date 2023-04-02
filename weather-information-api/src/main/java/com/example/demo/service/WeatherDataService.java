package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface WeatherDataService {
    ResponseEntity<Object> getWeatherData(String city, String date);
    ResponseEntity<Object> addWeatherData(Map<String, Object> weatherData);
}
