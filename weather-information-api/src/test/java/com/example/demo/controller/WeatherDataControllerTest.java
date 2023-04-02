package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.service.WeatherDataService;

public class WeatherDataControllerTest {

    private WeatherDataService weatherDataService;
    private WeatherDataController weatherDataController;

    @BeforeEach
    public void setUp() {
        weatherDataService = mock(WeatherDataService.class);
        weatherDataController = new WeatherDataController(weatherDataService);
    }

    @Test
    public void testGetWeatherDataWithMissingParameters() {
        ResponseEntity<?> response = weatherDataController.getWeatherData(null, null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Either date or city parameter is required.", response.getBody());
    }

    @Test
    public void testGetWeatherDataWithValidParameters() {
        Map<String, Object> weatherData = new HashMap<>();
        weatherData.put("city", "London");
        weatherData.put("date", "2023-04-02");
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(weatherData);
        when(weatherDataService.getWeatherData("London", "2023-04-02")).thenReturn((ResponseEntity<Object>) expectedResponse);
        ResponseEntity<?> actualResponse = weatherDataController.getWeatherData("London", "2023-04-02");
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testAddWeatherDataWithValidPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("city", "London");
        payload.put("date", "2023-04-02");
        ResponseEntity<?> expectedResponse = ResponseEntity.ok("Weather data added successfully.");
        when(weatherDataService.addWeatherData(any())).thenReturn((ResponseEntity<Object>) expectedResponse);
        ResponseEntity<?> actualResponse = weatherDataController.addWeatherData(payload);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testAddWeatherDataWithInvalidPayload() {
        Map<String, Object> payload = new HashMap<>();
        ResponseEntity<?> expectedResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding weather data to cache.");
        when(weatherDataService.addWeatherData(any())).thenThrow(new RuntimeException());
        ResponseEntity<?> actualResponse = weatherDataController.addWeatherData(payload);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testGetError() {
        ResponseEntity<?> expectedResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 Internal Server Error");
        ResponseEntity<?> actualResponse = weatherDataController.getError();
        assertEquals(expectedResponse, actualResponse);
    }
}
