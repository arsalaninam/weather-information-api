package com.example.demo.controller;

import com.example.demo.service.WeatherDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WeatherDataController {
    private final WeatherDataService weatherDataService;
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataController.class);

    public WeatherDataController(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    @GetMapping("/api/weather")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getWeatherData(@RequestParam(required = false) String city, @RequestParam(required = false) String date ) {
        if (date == null && city==null) {
            return ResponseEntity.badRequest().body("Either date or city parameter is required.");
        } else {
            return weatherDataService.getWeatherData(city,date);
        }
    }

    @PostMapping("/api/weather")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addWeatherData(@RequestBody Map<String, Object> payload) {
        try {
            weatherDataService.addWeatherData(payload);
            return ResponseEntity.ok("Weather data added successfully.");
        } catch (Exception e) {
            logger.error("Error adding weather data to cache", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding weather data to cache.");
        }
    }

    @GetMapping("/api/weather/error")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 Internal Server Error");
    }
}
