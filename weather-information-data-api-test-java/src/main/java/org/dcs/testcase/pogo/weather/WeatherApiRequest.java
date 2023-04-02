package org.dcs.testcase.pogo.weather;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WeatherApiRequest {
    private String city;
    private LocalDate date;
    private int temperature;
    private int humidity;

    @Override
    public String toString() {
        return "ClassPojo [city = " + city + ", date = " + date + " temperature = " + temperature + " humidity = " + humidity + "]";
    }
}
