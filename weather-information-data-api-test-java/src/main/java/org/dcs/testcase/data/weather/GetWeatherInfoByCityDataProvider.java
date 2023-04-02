package org.dcs.testcase.data.weather;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for posting weather information into database
 *
 * @author Arsalan Inam
 */
public class GetWeatherInfoByCityDataProvider {

    @DataProvider
    public static Object[][] getWeatherInfoByCity() {
        return new Object[][]{

                {"2021-04-03", "Budapest", 10, 51}
        };
    }
}
