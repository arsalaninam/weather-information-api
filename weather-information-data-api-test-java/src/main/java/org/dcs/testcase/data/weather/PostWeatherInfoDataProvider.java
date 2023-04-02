package org.dcs.testcase.data.weather;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for posting weather information into database
 *
 * @author Arsalan Inam
 */
public class PostWeatherInfoDataProvider {

    @DataProvider
    public static Object[][] postWeatherInfo() {
        return new Object[][]{
                {"2022-04-03", "Karachi", "26" , "76"},
                {"2021-04-03", "Istanbul", "16" , "66"},
                {"2023-04-03", "Frankfurt", "6" , "51"},
                {"2023-04-03", "Berlin", "5" , "51"},
                {"2023-04-02", "Berlin", "4" , "41"},
                {"2021-04-03", "Budapest", "10" , "51"},
                {"2020-04-03", "Paris", "11" , "61"},
                {"2023-04-03", "Dortmund", "7" , "44"},
                {"2023-04-03", "Munich", "4" , "33"},
                {"2023-04-02", "Munich", "25" , "44"},
                {"2023-04-01", "Munich", "3" , "55"},
                {"2023-05-03", "Munich", "26" , "44"},
                {"2023-06-03", "Mumbai", "34" , "31"},
                {"2023-04-03", "Islamabad", "25" , "53"},
                {"2023-04-03", "Barcelona", "18" , "53"},
                {"2023-04-03", "Lahore", "24" , "63"},
                {"2023-04-03", "Chennai", "29" , "73"},
                {"2023-04-03", "Karachi", "23" , "73"}

        };
    }
}
