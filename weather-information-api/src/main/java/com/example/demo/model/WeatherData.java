package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDate;

public class WeatherData implements Serializable {

    private String city;
    private LocalDate date;
    private int temperature;
    private int humidity;

    public WeatherData(String city, LocalDate date, int temperature, int humidity) {
        this.city = city;
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
