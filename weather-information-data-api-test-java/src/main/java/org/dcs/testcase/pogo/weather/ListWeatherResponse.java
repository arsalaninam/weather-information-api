package org.dcs.testcase.pogo.weather;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListWeatherResponse {
    List<WeatherApiResponse> weatherApiResponseList;

    @Override
    public String toString() {
        return "Weather Api Response [Weather Api Response=" + weatherApiResponseList + "]";
    }
}
