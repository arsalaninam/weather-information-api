package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public ResponseEntity<Object> getWeatherData(String city, String date) {
        List<Map<Object, Object>> result = new ArrayList<>();

        if (city != null && date != null) {
            String key = city + ":" + date;
            Map<Object, Object> weatherData = redisTemplate.opsForHash().entries(key);
            if (!weatherData.isEmpty()) {
                result.add(weatherData);
            }
        } else if (city != null) {
            Set<String> keys = redisTemplate.keys(city + ":*");
            for (String key : keys) {
                Map<Object, Object> weatherData = redisTemplate.opsForHash().entries(key);
                if (!weatherData.isEmpty()) {
                    result.add(weatherData);
                }
            }
        } else if (date != null) {
            Set<String> keys = redisTemplate.keys("*:" + date);
            for (String key : keys) {
                Map<Object, Object> weatherData = redisTemplate.opsForHash().entries(key);
                if (!weatherData.isEmpty()) {
                    result.add(weatherData);
                }
            }
        } else {
            return ResponseEntity.badRequest().build();
        }

        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> addWeatherData(Map<String, Object> weatherData) {
        String city = (String) weatherData.get("city");
        String date = (String) weatherData.get("date");
        String temperature = (String) weatherData.get("temperature");
        String humidity = (String) weatherData.get("humidity");

        if (city != null && date != null) {
            Map<String, Object> weatherDataMap = new HashMap<>();
            weatherDataMap.put("city", city);
            weatherDataMap.put("date", date);
            weatherDataMap.put("temperature", temperature);
            weatherDataMap.put("humidity", humidity);

            String key = city + ":" + date;

            // Delete any existing data for this key
            redisTemplate.delete(key);

            redisTemplate.opsForHash().putAll(key, weatherDataMap);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
